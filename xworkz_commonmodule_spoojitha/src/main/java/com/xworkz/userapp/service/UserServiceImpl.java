package com.xworkz.userapp.service;

import com.xworkz.userapp.dto.UserDto;
import com.xworkz.userapp.entity.UserEntity;
import com.xworkz.userapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    // Generates a random password
    public String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return password.toString();
    }

    @Override
    public boolean validateAndUser(UserDto dto, Model model) throws InvocationTargetException, IllegalAccessException {
        boolean errors = false;

        if (dto == null) {
            model.addAttribute("error", "User details cannot be null.");
            return false;
        }

        String nameRegex = "^[A-Z][a-zA-Z ]{2,49}$";
        Pattern namePattern = Pattern.compile(nameRegex);
        if (dto.getName() == null || !namePattern.matcher(dto.getName()).matches()) {
            model.addAttribute("nameError", "Invalid Name: Must start with an uppercase letter and contain only letters and spaces.");
            errors = true;
        }

        String phoneRegex = "^[9876]\\d{9}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (dto.getPhoneNumber() == null || !phonePattern.matcher(String.valueOf(dto.getPhoneNumber())).matches()) {
            model.addAttribute("phoneError", "Invalid Phone Number");
            errors = true;
        }

        String emailRegex = "^(?=.*[!@#$%^&*])[a-z0-9]+@gmail\\.com$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (dto.getEmail() == null || !emailPattern.matcher(dto.getEmail()).matches()) {
            model.addAttribute("emailError", "Invalid Email");
            errors = true;

        }

        if (errors) {
            return false;
        }

        // Generate, print, encrypt password
        String generatedPassword = generateRandomPassword();
        System.out.println("Generated Password for " + dto.getEmail() + " is: " + generatedPassword);

        UserEntity entity = new UserEntity();
        org.apache.commons.beanutils.BeanUtils.copyProperties(entity, dto);
        entity.setPassword(encryptPassword(generatedPassword));
        entity.setFailedAttempts(-1);

        repository.saveUser(entity);
        return true;
    }

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    @Override
    public boolean matchPassword(String enteredPassword, String storedHash) {
        return BCrypt.checkpw(enteredPassword, storedHash);
    }

    @Override
    public UserDto authenticateUser(String email, String password) {
        UserEntity userEntity = repository.findByEmail(email);

        if (userEntity == null) {
//            System.out.println("User not found.");
            log.info("User not found");
            return null;
        }

        // Check if account is locked
        if (userEntity.isAccountLocked()) {
            LocalDateTime lockedAt = userEntity.getLockTime();
            if (lockedAt != null && lockedAt.plusHours(24).isAfter(LocalDateTime.now())) {
//                System.out.println("Account is still locked.");
                log.info("Account is still locked.");
                return null;
            } else {
//                System.out.println("Unlocking account after 24 hours.");
                log.info("Unlocking account after 24 hours.");
                repository.resetAttempts(email);
            }
        }

        // Check password
        if (!BCrypt.checkpw(password, userEntity.getPassword())) {
            int attempts = userEntity.getFailedAttempts() + 1;
//            System.out.println("Failed login attempt count: " + attempts);
            log.info("Failed login attempt count: " + attempts);

            if (attempts >= 3) {
//                System.out.println("Locking account...");
                log.info("Locking account...");
                repository.lockAccount(email, LocalDateTime.now());
            } else {
                repository.updateFailedAttempts(email, attempts);
            }
            return null;
        }
        log.info("Resetting failed attempts on successful login");
//        System.out.println("Resetting failed attempts on successful login.");
        repository.resetAttempts(email);

        UserDto userDto = new UserDto();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(userDto, userEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) return null;

        UserDto dto = new UserDto();
        try {
            BeanUtils.copyProperties(dto, userEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
//            System.out.println("Error copying properties: " + e.getMessage());
            log.info("Error copying properties: " + e.getMessage());
        }
        return dto;
    }

    @Transactional
    @Override
    public boolean updateUserByEmail(String email, UserDto dto, Model model) {
        UserEntity existingUser = repository.findByEmail(email);
        if (existingUser == null) {
            model.addAttribute("error", "User not found.");
            return false;
        }

        String newPassword = existingUser.getPassword();
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            newPassword = encryptPassword(dto.getPassword());
        }

        int rowsUpdated = repository.updateByEmail(
                email,
                dto.getName(),
                String.valueOf(dto.getPhoneNumber()),
                dto.getLocation(),
                dto.getAge(),
                newPassword
        );

        if (rowsUpdated > 0) {
            model.addAttribute("successMessage", "Profile updated successfully!");
            return true;
        } else {
            model.addAttribute("error", "Failed to update profile.");
            return false;
        }
    }

    @Override
    @Transactional
    public boolean resetPassword(String email, String newPassword) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) {
            System.out.println("User not found for email: " + email);
            return false;
        }

        String encryptedPassword = encryptPassword(newPassword);
        repository.updatePassword(email, encryptedPassword);
        repository.resetAttempts(email);

        System.out.println("Password reset successfully for " + email);
        return true;
    }

    @Override
    public boolean saveUserWithPassword(UserDto dto, String password, Model model) {

        // Check if email already exists
        if (repository.findByEmail(dto.getEmail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return false;
        }

        // Check if phone number already exists
        if (repository.findByPhoneNumber(dto.getPhoneNumber()) != null) {
            model.addAttribute("error", "Phone Number already registered!");
            return false;
        }

        // Check if name already exists (Optional, depending on your business logic)
        if (repository.findByName(dto.getName()) != null) {
            model.addAttribute("error", "Username already taken!");
            return false;
        }

        // Generate a random password
        String generatedPassword = generateRandomPassword();
        log.info("Generated Password for " + dto.getEmail() + " is: " + generatedPassword);

        // Create and populate UserEntity
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(encryptPassword(generatedPassword));
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setLocation(dto.getLocation());
        entity.setAge(dto.getAge());
        entity.setDOB(dto.getDOB());
        entity.setGender(dto.getGender());
        entity.setFailedAttempts(-1);

        boolean saved=saveEmail(dto.getEmail(), generatedPassword);
        if (saved){
            log.info("Email sent Successfully");
        }
        // Save user in the repository
        repository.saveUser(entity);

        model.addAttribute("successMessage", "Registration successful! Please check your email for login credentials.");
        return true;
    }

    public boolean saveEmail(String email, String genPwd) {
        final String username = "poojithaspoojithas1@gmail.com";
        final String password = "urgf eqtf fqdb kjkn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Email Verification");
            message.setText("Dear User, your password is: " + genPwd);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            e.printStackTrace();

        }return false;
    }
    @Override
    @Transactional
    public boolean deleteUserByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);

        if (userEntity == null) {
            log.info("User not found for deletion: " + email);
            return false;
        }

        repository.deleteByEmail(email);
        log.info("User deleted successfully: " + email);
        return true;
    }


}

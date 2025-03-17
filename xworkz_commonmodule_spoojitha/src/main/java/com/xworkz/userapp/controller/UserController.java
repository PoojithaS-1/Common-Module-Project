package com.xworkz.userapp.controller;

import com.xworkz.userapp.dto.UserDto;
import com.xworkz.userapp.repository.UserRepository;
import com.xworkz.userapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

@Slf4j
@Component
@RequestMapping("/")
public class UserController {
@Autowired
   UserRepository repository;

    public UserController() {
        System.out.println("Controller default constructor is invoked");
    }

    @Autowired
    UserService userService;
    @RequestMapping("addUser")
    public String addUser( UserDto dto, Model model) throws InvocationTargetException, IllegalAccessException {

        if (dto == null || dto.getName() == null || dto.getName().trim().isEmpty()) {
            model.addAttribute("error", "User details cannot be null or empty.");
            log.error("Invalid UserDto: Missing required fields.");
            return "error.jsp";
        }

        if (repository.findByName(dto.getName()) != null) {
            model.addAttribute("error", "Username already taken!");
            model.addAttribute("dto", dto);
            log.warn("Username already exists: {}", dto.getName());
            return "error.jsp";
        }
        String generatedPassword = userService.generateRandomPassword();
        log.info("Generated Password: " + generatedPassword);

        boolean isValid = userService.validateAndSaveUser(dto,generatedPassword,model);

        if (!isValid) {
            model.addAttribute("dto", dto);

            return "signup.jsp";
        }

        model.addAttribute("name", dto.getName());
        model.addAttribute("location",dto.getLocation());
        model.addAttribute("successMessage", "User registered successfully!");
        return "response.jsp";
    }



    @PostMapping("/updateUser")
    public String updateUser(UserDto dto, Model model, HttpSession session) {
        log.info("In controller updateUser started");

        String email = (String) session.getAttribute("loggedInUserEmail");

        if (email == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "signin.jsp";
        }

        UserDto loggedInUser = userService.getUserByEmail(email); // Retrieve user details

        if (loggedInUser == null) {
            model.addAttribute("error", "User not found.");
            return "signin.jsp";
        }

        session.setAttribute("loggedInUser", loggedInUser); // Store in session
        model.addAttribute("loggedInUser", loggedInUser); // Store in model

        boolean isUpdated = userService.updateUserByEmail(email, dto, model);

        if (isUpdated) {
            model.addAttribute("successMessage", "Profile updated successfully!");
        } else {
            model.addAttribute("error", "Failed to update profile.");
        }

        log.info("In controller updateUser ended");
        return "update-profile.jsp";
    }


    @GetMapping("/signin")
    public String showSignInPage(HttpSession session) {
        session.setAttribute("captcha", generateCaptcha());
        return "signin.jsp";
    }

    @PostMapping("/signIn")
    public String signIn(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String captcha,
            Model model,
            HttpSession session
    ) {
        String sessionCaptcha = (String) session.getAttribute("captcha");

        if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
            model.addAttribute("error", "Invalid CAPTCHA. Try again.");
            session.setAttribute("captcha", generateCaptcha());
            return "signin.jsp";
        }

        UserDto user = userService.authenticateUser(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password.");
            return "signin.jsp";
        }

        if (user.getFailedAttempts() >= 3) {
            model.addAttribute("error", "Account locked due to multiple failed attempts.");
            return "signin.jsp";
        }

        session.setAttribute("loggedInUserEmail", user.getEmail());
        model.addAttribute("user", user);
        session.removeAttribute("captcha"); // Remove CAPTCHA after success
        return "welcome.jsp";
    }

    // CAPTCHA Generator
    private String generateCaptcha() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        return captcha.toString();
    }
    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestParam String email, @RequestParam String newPassword, Model model) {
        boolean isReset = userService.resetPassword(email, newPassword);

        if (!isReset) {
            model.addAttribute("error", "User not found.");
            return "error.jsp";
        }

        model.addAttribute("successMessage", "Password reset successfully!");
        return "signin.jsp";
    }
    @GetMapping("/editProfile")
    public String editProfile(HttpSession session, Model model) {

        String email = (String) session.getAttribute("loggedInUserEmail");

        if (email == null || email.isEmpty()) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "signin.jsp";
        }


        UserDto user = userService.getUserByEmail(email);

        if (user == null) {
            model.addAttribute("error", "User not found.");
            return "error.jsp";
        }

        model.addAttribute("loggedInUser", user);
        return "update-profile.jsp";
    }


    @PostMapping("/deleteUser")
    public String deleteUser(Model model, HttpSession session) {
        log.info("In controller deleteUser started");

        String email = (String) session.getAttribute("loggedInUserEmail");

        if (email == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "signin.jsp";
        }

        boolean isDeleted = userService.deleteUserByEmail(email);

        if (isDeleted) {
            session.invalidate(); // Clearing session after deletion
            model.addAttribute("successMessage", "Account deleted successfully!");
            return "deleteUser.jsp";
        } else {
            model.addAttribute("error", "Failed to delete account.");
            return "deleteUser.jsp";
        }
    }

}

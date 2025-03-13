package com.xworkz.userapp.service;

import com.xworkz.userapp.dto.UserDto;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;


public interface UserService {
    String generateRandomPassword();

    boolean validateAndUser(UserDto dto, Model model) throws InvocationTargetException, IllegalAccessException;

    String encryptPassword(String password);

    boolean matchPassword(String enteredPassword, String storedHash);

    UserDto authenticateUser(String email, String password);

    UserDto getUserByEmail(String email);

    boolean updateUserByEmail(String email, UserDto dto, Model model);

    boolean resetPassword(String email, String newPassword);

    boolean saveUserWithPassword(UserDto dto, String password, Model model);


    boolean deleteUserByEmail(String email);


}

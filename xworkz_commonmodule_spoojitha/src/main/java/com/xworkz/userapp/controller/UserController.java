package com.xworkz.userapp.controller;

import com.xworkz.userapp.dto.UserDto;
import com.xworkz.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Component
@RequestMapping("/")
public class UserController {

    public UserController() {
        System.out.println("Controller default constructor is invoked");
    }

    @Autowired
    UserService userService;

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


    @PostMapping("/updateUser")
    public String updateUser(UserDto dto, Model model, HttpSession session) {
        System.out.println("In controller updateUser started");
        String email = (String) session.getAttribute("loggedInUserEmail");

        if (email == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "signup.jsp";
        }

        boolean isUpdated = userService.updateUserByEmail(email, dto, model);

        if (isUpdated) {

            model.addAttribute("successMessage", "Profile updated successfully!");
        } else {
            model.addAttribute("error", "Failed to update profile.");
        }
        System.out.println("In controller updateUser ended");
        return "update-profile.jsp";
    }


    @RequestMapping("addUser")
    public String addUser( UserDto dto, Model model) {

        String generatedPassword = userService.generateRandomPassword();
        System.out.println("Generated Password: " + generatedPassword);

        boolean isValid = userService.saveUserWithPassword(dto, generatedPassword, model);

        if (!isValid) {
            return "error.jsp";
        }
//        if(bindingResult.hasErrors()){
//            System.out.println("The Errors are===");
//        }else {
//            System.out.println(bindingResult.getAllErrors());
//        }

        model.addAttribute("name", dto.getName());
        return "response.jsp";
    }

    @PostMapping("/signIn")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
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
        return "welcome.jsp";
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
    @PostMapping("/deleteUser")
    public String deleteUser(Model model, HttpSession session) {
        System.out.println("In controller deleteUser started");

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

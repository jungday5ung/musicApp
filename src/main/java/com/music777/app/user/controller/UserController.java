package com.music777.app.user.controller;

import com.music777.app.user.dto.UserDTO;
import com.music777.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public String showSavePage() {
        return "main/save";
    }

    @PostMapping("/checkUserId")
    @ResponseBody
    public String checkUserId(@RequestParam(name = "id") String id) {
        boolean exists = userService.isUserIdExists(id);
        return exists ? "exists" : "available";
    }

    @PostMapping("/checkUserEmail")
    @ResponseBody
    public String checkUserEmail(@RequestParam(name = "email") String email) {
        boolean exists = userService.isUserEmailExists(email);
        return exists ? "exists" : "available";
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "id") String id,
                       @RequestParam(name = "password") String password,
                       @RequestParam(name = "password2") String password2,
                       @RequestParam(name = "name") String name,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "phoneNumber") String phoneNumber,
                       Model model) {
        if (!password.equals(password2)) {
            model.addAttribute("error", "Passwords do not match.");
            return "main/save";
        }

        if (userService.isUserIdExists(id)) {
            model.addAttribute("error", "User ID already exists.");
            return "main/save";
        }

        if (userService.isUserEmailExists(email)) {
            model.addAttribute("error", "Email already exists.");
            return "main/save";
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setPassword(password);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPhoneNumber(phoneNumber);

        userService.save(userDTO);

        return "redirect:/main/login";

    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "main/login"; // main/login.html 페이지로 매핑
    }
}

package com.music777.app.user.controller;

import com.music777.app.user.dto.UserDTO;
import com.music777.app.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired // UserService 빈을 자동으로 주입합니다.
    private UserService userService;



    // 관리자 매핑 -> 회원관리
    @GetMapping("/admin/user-management")
    public String showUserManagementPage(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null && "admin".equals(userDTO.getRole())) {
            model.addAttribute("user", userDTO);
            return "admin/user-management"; // admin/user-management.html 템플릿으로 매핑
        }
        return "redirect:/main/home"; // 관리자가 아니면 홈 페이지로 리다이렉트
    }




    // 회원관리 -> 회원조회
    @GetMapping("/admin/search")
    public String showUserSearch(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null && "admin".equals(userDTO.getRole())) {
            model.addAttribute("user", userDTO);
        }
        return "admin/search"; // mypage.html 템플릿으로 매핑
    }

    @GetMapping("/admin/searchUser")
    @ResponseBody
    public UserDTO searchUser(@RequestParam("query") String query) {
        return userService.findUserById(query);
    }


    // 회원관리 -> 회원전체조회
    @GetMapping("/admin/allSearch")
    public String showUserAllSearch(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null && "admin".equals(userDTO.getRole())) {
            model.addAttribute("user", userDTO);
        }
        return "admin/allSearch"; // mypage.html 템플릿으로 매핑
    }

    @GetMapping("/admin/getAllUsers")
    @ResponseBody
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }








    // 회원관리 -> 회원삭제

    @DeleteMapping("/admin/deleteUser")
    @ResponseBody
    public void deleteUser(@RequestParam("userNo") int userNo) {
        userService.deleteUserById(userNo);
    }


    @GetMapping("/admin/delete")
    public String showUserDelete(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null && "admin".equals(userDTO.getRole())) {
            model.addAttribute("user", userDTO);
        }
        return "admin/delete"; // mypage.html 템플릿으로 매핑
    }

}

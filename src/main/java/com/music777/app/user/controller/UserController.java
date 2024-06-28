package com.music777.app.user.controller;

import com.music777.app.user.dto.UserDTO;
import com.music777.app.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 컨트롤러 어노테이션: 이 클래스가 Spring MVC의 컨트롤러임을 나타냅니다.
@Controller
@RequestMapping("/main") // 이 컨트롤러의 모든 요청은 "/main" 경로로 매핑됩니다.
public class UserController {

    @Autowired // UserService 빈을 자동으로 주입합니다.
    private UserService userService;

    // GET 요청으로 "/register" 경로에 접근할 때 호출되는 메서드
    @GetMapping("/register")
    public String showRegisterPage() {
        return "main/register"; // "main/register.html" 뷰를 반환합니다.
    }

    // POST 요청으로 "/checkUserId" 경로에 접근할 때 호출되는 메서드
    // 사용자의 ID가 이미 존재하는지 확인하는 역할을 합니다.
    @PostMapping("/checkUserId")
    @ResponseBody // 반환값을 뷰가 아닌 응답 본문에 직접 씁니다.
    public String checkUserId(@RequestParam(name = "id") String id) {
        boolean exists = userService.isUserIdExists(id); // ID 존재 여부 확인
        return exists ? "exists" : "available"; // 존재하면 "exists", 아니면 "available" 반환
    }

    // POST 요청으로 "/checkUserEmail" 경로에 접근할 때 호출되는 메서드
    // 사용자의 이메일이 이미 존재하는지 확인하는 역할을 합니다.
    @PostMapping("/checkUserEmail")
    @ResponseBody // 반환값을 뷰가 아닌 응답 본문에 직접 씁니다.
    public String checkUserEmail(@RequestParam(name = "email") String email) {
        boolean exists = userService.isUserEmailExists(email); // 이메일 존재 여부 확인
        return exists ? "exists" : "available"; // 존재하면 "exists", 아니면 "available" 반환
    }

    // POST 요청으로 "/register" 경로에 접근할 때 호출되는 메서드
    // 사용자 등록 처리를 합니다.
    @PostMapping("/register")
    public String register(@RequestParam(name = "id") String id,
                       @RequestParam(name = "password") String password,
                       @RequestParam(name = "password2") String password2,
                       @RequestParam(name = "name") String name,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "phoneNumber") String phoneNumber,
                       Model model) {

        // 비밀번호와 비밀번호 확인이 일치하지 않을 경우
        if (!password.equals(password2)) {
            model.addAttribute("error", "Passwords do not match."); // 모델에 에러 메시지 추가
            return "main/register"; // 다시 "main/save" 뷰로 이동
        }

        // ID가 이미 존재할 경우
        if (userService.isUserIdExists(id)) {
            model.addAttribute("error", "User ID already exists."); // 모델에 에러 메시지 추가
            return "main/register"; // 다시 "main/register" 뷰로 이동
        }

        // 이메일이 이미 존재할 경우
        if (userService.isUserEmailExists(email)) {
            model.addAttribute("error", "Email already exists."); // 모델에 에러 메시지 추가
            return "main/register"; // 다시 "main/save" 뷰로 이동
        }

        // UserDTO 객체를 생성하고 폼에서 입력받은 값을 설정
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setPassword(password); // 실제로는 여기서 비밀번호를 인코딩해야 함
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPhoneNumber(phoneNumber);

        // UserService를 통해 사용자 정보를 저장
        userService.register(userDTO);

        // "main/login" 경로로 리다이렉트
        return "redirect:/main/login";
    }

    // GET 요청으로 "/login" 경로에 접근할 때 호출되는 메서드
    // 로그인 페이지를 보여줍니다.
    @GetMapping("/login")
    public String showLoginPage() {
        return "main/login"; // "main/login.html" 페이지로 매핑
    }

    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입

    //
    @PostMapping("/login")
    public String login(@RequestParam(name = "id") String id,
                        @RequestParam(name = "password") String password,
                        Model model, HttpSession session) {
        UserDTO userDTO = userService.login(id, password);
        if (userDTO != null) {
            session.setAttribute("user", userDTO); // 세션에 사용자 정보를 저장
            return "redirect:/main/home"; // 로그인 성공 후 홈 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "Invalid id or password.");
            return "main/login"; // 로그인 실패 시 로그인 페이지로 다시 이동
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/main/login?logout=true"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }


        return "main/home"; // main/home.html 템플릿으로 매핑
    }

    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인

}

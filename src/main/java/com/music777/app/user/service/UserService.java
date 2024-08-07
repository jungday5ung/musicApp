package com.music777.app.user.service;

import com.music777.app.user.dto.UserDTO;
import com.music777.app.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 이 클래스는 사용자와 관련된 비즈니스 로직을 담당하는 서비스 클래스입니다.
@Service
public class UserService {

    // UserMapper 빈을 자동으로 주입합니다.
    @Autowired
    private UserMapper userMapper;

    // PasswordEncoder 빈을 자동으로 주입합니다.
    // 현재는 주석 처리되어 있으며, 주석을 제거하고 PasswordEncoder 빈을 사용하면 비밀번호를 암호화할 수 있습니다.
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // 주어진 사용자 ID가 존재하는지 확인하는 메서드입니다.
    public boolean isUserIdExists(String userId) {
        return userMapper.checkUserIdExists(userId) > 0; // UserMapper를 사용하여 ID 존재 여부를 확인합니다.
    }

    // 주어진 사용자 이메일이 존재하는지 확인하는 메서드입니다.
    public boolean isUserEmailExists(String userEmail) {
        return userMapper.checkUserEmailExists(userEmail) > 0; // UserMapper를 사용하여 이메일 존재 여부를 확인합니다.
    }

    // 주어진 사용자 정보를 저장하는 메서드입니다.
    public void register(UserDTO userDTO) {
        // 비밀번호 암호화 부분 (현재는 주석 처리되어 있음)
//        // 비밀번호 암호화
//        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
//        userDTO.setPassword(encryptedPassword);

        // 기본 역할 설정
        userDTO.setRole("user");
        // 관리자 등록 시 역할 설정
        if ("admin".equals(userDTO.getId())) {
            userDTO.setRole("admin");
        }

        // 디버깅용 출력
        System.out.println("Role 설정 확인: " + userDTO.getRole());

        // UserMapper를 사용하여 사용자 정보를 데이터베이스에 저장합니다.
        userMapper.register(userDTO);
    }

    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입
    // 여기까지는 회원가입

    public UserDTO login(String id, String password) {
        return userMapper.findByIdAndPassword(id, password); // ID와 비밀번호로 사용자를 찾습니다.

    }
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인
    // 여기까지 로그인

    public void updatePassword(UserDTO userDTO) {
        userMapper.updatePassword(userDTO);
    }

    public void updatePhoneNumber(UserDTO userDTO) {
        userMapper.updatePhoneNumber(userDTO);
    }
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정
    // 여기까지 내정보 수정


    // 비밀번호가 일치하는지 확인합니다.
    public boolean checkPassword(String id, String rawPassword) {
        String storedPassword = userMapper.getPasswordById(id);
        return storedPassword != null && storedPassword.equals(rawPassword);
    }

    // 사용자를 삭제합니다.
    public void deleteUser(String id) {
        userMapper.deleteUserById(id);
    }



    // 관리자
    // 관리자 -> 회원조회
    public UserDTO findUserById(String id) {
        return userMapper.findUserById(id);
    }

    // 관리자 -> 회원전체 조회
    public List<UserDTO> findAllUsers() {
        return userMapper.findAllUsers();
    }


    // 관리자 -> 회원삭제
    public void deleteUserById(int userNo) {
        userMapper.deleteUserById2(userNo);
    }
}

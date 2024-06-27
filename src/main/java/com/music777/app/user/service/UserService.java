package com.music777.app.user.service;

import com.music777.app.user.dto.UserDTO;
import com.music777.app.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean isUserIdExists(String userId) {
        return userMapper.checkUserIdExists(userId) > 0;
    }

    public boolean isUserEmailExists(String userEmail) {
        return userMapper.checkUserEmailExists(userEmail) > 0;
    }

    public void save(UserDTO userDTO) {
        userMapper.save(userDTO);
    }
}

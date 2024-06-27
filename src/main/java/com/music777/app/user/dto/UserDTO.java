package com.music777.app.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {

    private int userNo; // USER_NO
    private String id; // ID
    private String password; // PASSWORD
    private String name; // NAME
    private String email; // EMAIL
    private String phoneNumber; // PHONE_NUMBER
    private Timestamp createdAt; // CREATED_AT
    private String role; // ROLE
    private char del; // DEL
}

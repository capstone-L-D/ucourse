package com.example.user_course_sr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private String userId;
    private String userName;
    private String userEmail;
    private String password;
}

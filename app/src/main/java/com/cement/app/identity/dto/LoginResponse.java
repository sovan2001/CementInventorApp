package com.cement.app.identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long employeeId;
    private String username;
    private String name;
    private Long enterpriseId;
}
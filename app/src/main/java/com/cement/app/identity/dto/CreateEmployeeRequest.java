package com.cement.app.identity.dto;

import lombok.Data;

@Data
public class CreateEmployeeRequest {

    private String employeeCode;
    private String name;
    private String username;
    private String password;
}

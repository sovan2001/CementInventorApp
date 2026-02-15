package com.cement.app.inventory.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateCustomerRequest {

    private String customerName;
    private String phone;
    private String address;
    private BigDecimal creditLimit;
}
package com.cement.app.inventory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {

    private String productName;
    private String unit;

}

package com.ecommerce.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    public ProductDto(Long id2, String productName2, Number prize, String discription2, String img2) {
        //TODO Auto-generated constructor stub
    }
    private Long id;
    private String productName;
    private Number price;
    private String discription;
    private String img;
}

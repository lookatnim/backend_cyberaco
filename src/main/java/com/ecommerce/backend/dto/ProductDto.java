package com.ecommerce.backend.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.backend.entity.ImageModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private Number price;
    private String discription;
    private Set<ImageModel> images = new HashSet<>();
}

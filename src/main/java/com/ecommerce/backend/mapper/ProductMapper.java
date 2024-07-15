package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.ProductDto;
import com.ecommerce.backend.entity.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
            product.getId(),
            product.getProductName(),
            product.getPrize(),
            product.getDiscription(),
            product.getImages()
        );
    }

    public static Product mapToProduct(ProductDto product){
        return new Product(
            product.getId(),
            product.getProductName(),
            product.getPrice(),
            product.getDiscription(),
            product.getImages()
        );
    }
}

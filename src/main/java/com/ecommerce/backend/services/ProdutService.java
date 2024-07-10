package com.ecommerce.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.ProductDto;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.mapper.ProductMapper;
import com.ecommerce.backend.repo.ProductRepository;

@Service
public class ProdutService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto){
        Product prd = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(prd);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    public ProductDto getProductById(Long id) {
        Product prd = productRepository.findById(id).orElseThrow();
        return ProductMapper.mapToProductDto(prd);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> prd = (List<Product>) productRepository.findAll();
        return prd.stream().map((p) -> ProductMapper.mapToProductDto(p))
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product prd = productRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Product not found")
        );
        prd.setProductName(productDto.getProductName());
        prd.setPrize(productDto.getPrice());
        prd.setDiscription(productDto.getDiscription());
        Product updateProduct = productRepository.save(prd);
        return ProductMapper.mapToProductDto(updateProduct);
    }


    public void deleteProduct(Long id) {
        Product prd = productRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Product not found")
        );

        productRepository.deleteById(id);
        
    }
    
}

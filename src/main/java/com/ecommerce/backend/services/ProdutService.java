package com.ecommerce.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.ProductDto;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.mapper.ProductMapper;
import com.ecommerce.backend.repo.ProductRepository;

@Service
public class ProdutService {
    private final ProductRepository productRepository;

    public ProdutService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(ProductDto productDto){
        System.out.println("create(|||||||||||||)");
        Product prd = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(prd);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    public ProductDto getProductById(Long id) {
        Product prd = productRepository.findById(id).orElseThrow();
        return ProductMapper.mapToProductDto(prd);
    }

    public List<ProductDto> getAllProducts() {
        // Log entry into the method
        System.out.println("Entering getAllProducts method");
    
        // Fetch all products from the repository
        List<Product> prd = (List<Product>) productRepository.findAll();
    
        // Log the number of products fetched
        System.out.println("Number of products fetched: " + prd.size());
    
        // Map each product to a ProductDto and collect the results in a list
        List<ProductDto> productDtos = prd.stream()
            .map((product) -> {
                // Log each product before mapping
                System.out.println("Mapping product: " + product);
                return ProductMapper.mapToProductDto(product);
            })
            .collect(Collectors.toList());
    
        // Log the number of DTOs created
        System.out.println("Number of product DTOs created: " + productDtos.size());
    
        return productDtos;
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

package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.backend.dto.ProductDto;
import com.ecommerce.backend.services.ProdutService;

@RequestMapping("pub")
public class ProductController {

    private ProdutService produtService;
    @PostMapping("/addproduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct = produtService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
    }

    @GetMapping("/getproduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long prdid){
        ProductDto productDto = produtService.getProductById(prdid);
        return ResponseEntity.ok(productDto);
    }
    
    @GetMapping("getproducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto = produtService.getAllProducts();
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("editProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long prdid, @RequestBody ProductDto productDto){
        ProductDto updateProduct = produtService.updateProduct(prdid, productDto);
        return ResponseEntity.ok(updateProduct);
    }
    
    @DeleteMapping("deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long prdid){
        produtService.deleteProduct(prdid);
        return ResponseEntity.ok("Product deleted successfully");
    }
}

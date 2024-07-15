package com.ecommerce.backend.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.backend.dto.ProductDto;
import com.ecommerce.backend.services.ProdutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecommerce.backend.entity.ImageModel;
import com.ecommerce.backend.entity.Product;

@RequestMapping("/products")
@RestController
public class ProductController {

    private ProdutService produtService;

    public ProductController(ProdutService produtService) {
        this.produtService = produtService;
    }
    @PostMapping(value = "/addproduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<ProductDto> createProduct(
        @RequestPart("product") String productString,
        @RequestPart("imageFile") MultipartFile[] file) {
    
    try {
        // Deserialize the productString to ProductDto
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = objectMapper.readValue(productString, ProductDto.class);
        System.out.println("String " +productString);
        System.out.println("object " +productDto);

        // Handle file uploads
        Set<ImageModel> images = uploadImage(file);
        productDto.setImages(images);
        
        // Save product
        ProductDto savedProduct = produtService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    } catch (IOException e) {
        System.out.println(e.getMessage() + " " + "sdadasdasdasasdas");
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    public Set<ImageModel> uploadImage(MultipartFile[] files) throws IOException{
        Set<ImageModel> imgModels = new HashSet<>();

        for(MultipartFile multipartFile : files){
                ImageModel imageModel = new ImageModel(
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getBytes()
                );
                imgModels.add(imageModel);
        }

        return imgModels;
    }

    @GetMapping("/getproduct/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long prdid){
        ProductDto productDto = produtService.getProductById(prdid);
        return ResponseEntity.ok(productDto);
    }
    
    @GetMapping("/pub/all")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDto = produtService.getAllProducts();
        System.out.println("lllllllllllllllllllllllllllllllllllllllllll" +productDto);
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

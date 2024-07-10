package com.ecommerce.backend.repo;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.backend.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

    
}
package com.ecommerce.backend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.User;

public interface  CartRepository extends CrudRepository<Cart, Long>{
    List<Cart> findByUser(User user);
}

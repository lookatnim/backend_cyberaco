package com.ecommerce.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.services.CartService;
import com.ecommerce.backend.entity.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/addtocart/{productid}/{userid}")
public ResponseEntity<String> addCart(@PathVariable(name = "productid") Long productId,
                                      @PathVariable(name = "userid") Long userId) {
    try {
        System.out.println("prod id" + productId);
        System.out.println("User id" + userId);

        cartService.addtocart(productId, userId);
        return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to add product to cart", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("/user/{userid}")
    public List<Cart> getCartItemsByUser(@PathVariable(name = "userid") Long userId) {
        return cartService.getCartItemsByUser(userId);
    }
}

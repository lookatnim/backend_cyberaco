package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repo.CartRepository;
import com.ecommerce.backend.repo.ProductRepository;
import com.ecommerce.backend.repo.UserRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // public void addtocart(Long productId, Long userId){
    //     Product product = productRepository.findById(productId).get();
        
    //     User user = null;
    //     if(userId != null){
    //         user = userRepository.findById(userId).get();
    //     }
    //     if(product != null && user != null){
    //         Cart cart = new Cart(product, user);
    //         cartRepository.save(cart);

    //     }
    // }

    public void addtocart(Long productId, Long userId) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (product != null && user != null) {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product or User not found");
        }
    }
    public List<Cart> getCartItemsByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        System.out.println(user);
        if (user != null) {
            System.out.println(cartRepository.findByUser(user));
            return cartRepository.findByUser(user);
        }
        return null;
    }


}

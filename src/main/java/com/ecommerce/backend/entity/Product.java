package com.ecommerce.backend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Table(name = "products")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private Number prize;
    
    @Column(nullable = false)
    private String discription;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable( name = "product_images",
        joinColumns = {
            @JoinColumn(name = "product_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "image_id")

        }
    )
    private Set<ImageModel> images = new HashSet<>();
}

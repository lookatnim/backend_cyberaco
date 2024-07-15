package com.ecommerce.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="image_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ImageModel {

    public ImageModel(String name, String type, byte[] bs) {
        this.name = name;
        this.type = type;
        this.picByte = bs;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Column(length = 5000000)
    private byte[] picByte;

}

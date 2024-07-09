package com.ecommerce.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private Integer userRole;
}
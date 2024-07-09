package com.ecommerce.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private Integer userRole;


}

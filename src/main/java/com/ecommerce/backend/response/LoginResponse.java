package com.ecommerce.backend.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String mobileNumber;
    private String password;
    private Integer userRole;
    private String token;
    private long expiresIn;
    
    

    public LoginResponse(Long id,String firstName,String lastName,String userName,String email,String mobileNumber,Integer userRole,String token, long expiresIn) {
        System.out.println(token + "||||||||||||||||| " + expiresIn);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
        this.token = token;
        this.expiresIn = expiresIn;
    }
}
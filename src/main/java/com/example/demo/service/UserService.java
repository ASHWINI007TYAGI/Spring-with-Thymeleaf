package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.payload.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
}

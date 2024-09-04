package com.example.demo.service;

import com.example.demo.dto.response.user.GetSignInUserResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
}

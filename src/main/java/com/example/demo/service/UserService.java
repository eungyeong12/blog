package com.example.demo.service;

import com.example.demo.dto.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}

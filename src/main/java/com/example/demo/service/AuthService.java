package com.example.demo.service;

import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
}

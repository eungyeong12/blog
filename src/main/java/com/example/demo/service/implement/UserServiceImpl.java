package com.example.demo.service.implement;

import com.example.demo.domain.User;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.GetSignInUserResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        User user = null;

        try {

            user = userRepository.findByEmail(email);
            if(user == null) return GetUserResponseDto.noExistUser();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(user);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        User user = null;

        try {

            user = userRepository.findByEmail(email);
            if(user == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(user);
    }
}

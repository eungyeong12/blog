package com.example.demo.service.implement;

import com.example.demo.domain.User;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.user.GetSignInUserResponseDto;
import com.example.demo.repository.UserReporitory;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserReporitory userReporitory;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        User user = null;

        try {

            user = userReporitory.findByEmail(email);
            if(user == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(user);
    }
}

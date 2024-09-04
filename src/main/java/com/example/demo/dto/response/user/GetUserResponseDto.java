package com.example.demo.dto.response.user;


import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.domain.User;
import com.example.demo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponseDto extends ResponseDto {

    private String email;
    private String nickname;
    private String profileImage;

    private GetUserResponseDto(User user) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
    }

    public static ResponseEntity<GetUserResponseDto> success(User user) {
        GetUserResponseDto result = new GetUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}

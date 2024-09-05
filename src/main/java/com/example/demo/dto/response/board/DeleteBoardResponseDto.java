package com.example.demo.dto.response.board;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteBoardResponseDto extends ResponseDto {

    private DeleteBoardResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteBoardResponseDto> success() {
        DeleteBoardResponseDto result = new DeleteBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSTION, ResponseMessage.NO_PERMISSTION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}

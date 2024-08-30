package com.example.demo.service;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}

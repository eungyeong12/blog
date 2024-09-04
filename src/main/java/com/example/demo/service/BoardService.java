package com.example.demo.service;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.response.board.GetBoardResponseDto;
import com.example.demo.dto.response.board.GetFavoriteListResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.dto.response.board.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email);
}

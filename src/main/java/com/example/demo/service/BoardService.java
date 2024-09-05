package com.example.demo.service;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Long boardId, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email);
}

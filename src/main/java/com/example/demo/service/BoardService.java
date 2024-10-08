package com.example.demo.service;

import com.example.demo.dto.request.board.PatchBoardRequestDto;
import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.board.*;
import org.hibernate.sql.Delete;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardId);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Long boardId, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Long boardId, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardId, String email);
}

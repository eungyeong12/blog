package com.example.demo.api;

import com.example.demo.dto.request.board.PatchBoardRequestDto;
import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.board.*;
import com.example.demo.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable("id") Long boardId
    ) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardId);
        return response;
    }

    @GetMapping("/{boardId}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
            @PathVariable("boardId") Long boardId
    ) {
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardId);
        return response;
    }

    @GetMapping("/{boardId}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getComentList(
            @PathVariable("boardId") Long boardId
    ) {
        ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardId);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @RequestBody @Valid PostCommentRequestDto requestBody,
            @PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal String email
            ) {
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardId, email);
        return response;
    }

    @PutMapping("/{id}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @PathVariable("id") Long boardId,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardId, email);
        return response;
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
            @RequestBody @Valid PatchBoardRequestDto requestBody,
            @PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(requestBody, boardId, email);
        return response;
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardId, email);
        return response;
    }
}

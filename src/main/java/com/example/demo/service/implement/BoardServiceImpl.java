package com.example.demo.service.implement;

import com.example.demo.domain.Board;
import com.example.demo.domain.Image;
import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.UserReporitory;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserReporitory userReporitory;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {

            boolean existedUser = userReporitory.existsByEmail(email);
            if(!existedUser) return PostBoardResponseDto.notExistUser();

            Board board = new Board(dto, email);
            boardRepository.save(board);

            long boardId = board.getId();
            List<String> boardImageList = dto.getBoardImageList();
            List<Image> images = new ArrayList<>();

            for(String image : boardImageList) {
                Image imageEntity = new Image(boardId, image);
                images.add(imageEntity);
            }

            imageRepository.saveAll(images);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }
}

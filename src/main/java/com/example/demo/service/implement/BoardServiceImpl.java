package com.example.demo.service.implement;

import com.example.demo.domain.Board;
import com.example.demo.domain.Image;
import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.resultSet.GetBoardResultSet;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId) {

        GetBoardResultSet resultSet = null;
        List<Image> images = new ArrayList<>();

        try {

            resultSet = boardRepository.getBoard(boardId);
            if(resultSet == null) return GetBoardResponseDto.notExistBoard();

            images = imageRepository.findByBoardId(boardId);

            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board = optionalBoard.get();
            board.increaseViewCount();
            boardRepository.save(board);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.sucess(resultSet, images);
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try {

            boolean existedUser = userRepository.existsByEmail(email);
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

package com.example.demo.service.implement;

import com.example.demo.common.ResponseCode;
import com.example.demo.domain.Board;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Favorite;
import com.example.demo.domain.Image;
import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.*;
import com.example.demo.repository.*;
import com.example.demo.repository.resultSet.GetBoardResultSet;
import com.example.demo.repository.resultSet.GetCommentListResultSet;
import com.example.demo.repository.resultSet.GetFavoriteListResultSet;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;

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
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsById(boardId);
            if(!existedBoard) return GetFavoriteListResponseDto.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardId);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardId) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsById(boardId);
            if(!existedBoard) return GetCommentListResponseDto.noExistBoard();

            resultSets = commentRepository.getCommentList(boardId);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(resultSets);
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

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Long boardId, String email) {

        try {

            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board = optionalBoard.get();
            if(board == null) return PostCommentResponseDto.noExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PostCommentResponseDto.noExistUser();

            Comment comment = new Comment(dto, boardId, email);
            commentRepository.save(comment);

            board.increaseCommentCount();
            boardRepository.save(board);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email) {

        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.noExistUser();

            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board = optionalBoard.get();
            if(board == null) return PutFavoriteResponseDto.noExistBoard();

            Favorite favorite = favoriteRepository.findByBoardIdAndUserEmail(boardId, email);
            if(favorite == null) {
                favorite = new Favorite(email, boardId);
                favoriteRepository.save(favorite);
                board.increaseFavoriteCount();
            } else {
                favoriteRepository.delete(favorite);
                board.decreaseFavoriteCount();
            }

            boardRepository.save(board);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardId, String email) {

        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeleteBoardResponseDto.noExistUser();

            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board = optionalBoard.get();
            if(board == null) return DeleteBoardResponseDto.noExistBoard();

            String writerEmail = board.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeleteBoardResponseDto.noPermission();

            imageRepository.deleteByBoardId(boardId);
            commentRepository.deleteByBoardId(boardId);
            favoriteRepository.deleteByBoardId(boardId);

            boardRepository.delete(board);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return DeleteBoardResponseDto.success();
    }
}

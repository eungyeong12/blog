package com.example.demo.repository;

import com.example.demo.domain.Board;
import com.example.demo.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    boolean existsById(Long boardId);
    Optional<Board> findById(Long boardId);

    @Query(
            value=
                    "SELECT " +
                            "B.id AS id, " +
                            "B.title AS title, " +
                            "B.content AS content, " +
                            "B.write_datetime AS writerDatetime, " +
                            "B.writer_email AS writerEmail, " +
                            "U.nickname AS writerNickname, " +
                            "U.profile_image AS writerProfileImage " +
                            "FROM board AS B " +
                            "INNER JOIN users AS U " +
                            "ON B.writer_email = U.email " +
                            "WHERE B.id = ?1 ",
            nativeQuery = true
    )
    GetBoardResultSet getBoard(Long boardId);
}

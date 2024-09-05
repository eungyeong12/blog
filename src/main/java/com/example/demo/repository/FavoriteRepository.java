package com.example.demo.repository;

import com.example.demo.domain.Favorite;
import com.example.demo.domain.primaryKey.FavoritePK;
import com.example.demo.repository.resultSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {

    Favorite findByBoardIdAndUserEmail(Long boardId, String userEmail);

    @Query(
            value=
                    "SELECT " +
                            "U.email AS email, " +
                            "U.nickname AS nickname, " +
                            "U.profile_image AS profileImage " +
                            "FROM favorite AS F " +
                            "INNER JOIN users AS U " +
                            "ON F.user_email = U.email " +
                            "WHERE F.board_id = ?1",
            nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Long boardId);

    @Transactional
    void deleteByBoardId(Long boardId);
}

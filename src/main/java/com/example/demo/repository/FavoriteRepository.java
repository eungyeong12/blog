package com.example.demo.repository;

import com.example.demo.domain.Favorite;
import com.example.demo.domain.primaryKey.FavoritePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {

    Favorite findByBoardIdAndUserEmail(Long boardId, String userEmail);
}

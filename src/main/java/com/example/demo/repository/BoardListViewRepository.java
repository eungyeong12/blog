package com.example.demo.repository;

import com.example.demo.domain.BoardListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListView, Long> {
}

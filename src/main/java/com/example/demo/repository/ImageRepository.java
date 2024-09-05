package com.example.demo.repository;

import com.example.demo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByBoardId(Long boardId);

    @Transactional
    void deleteByBoardId(Long boardId);
}

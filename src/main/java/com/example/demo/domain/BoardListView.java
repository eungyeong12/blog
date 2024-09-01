package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardListView {
    @Id
    private Long id;

    private String title;

    private String content;

    private String titleImage;

    private int viewCount;

    private int favoriteCount;

    private int commentCount;

    private String writeDatetime;

    private String writerEmail;

    private String writerProfileImage;
}

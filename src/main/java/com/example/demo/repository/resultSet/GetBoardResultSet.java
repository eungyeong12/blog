package com.example.demo.repository.resultSet;

public interface GetBoardResultSet {
    Long getId();
    String getTitle();
    String getContent();
    String getWriteDatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();
}

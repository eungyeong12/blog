package com.example.demo.domain;

import com.example.demo.domain.primaryKey.FavoritePK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(FavoritePK.class)
public class Favorite {
    @Id
    private String userEmail;

    @Id
    private Long board_id;
}

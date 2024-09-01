package com.example.demo.domain;

import com.example.demo.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String telNumber;

    private String address;

    private String addressDetail;

    private String profileImage;

    private boolean agreePersonal;

    public User(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.agreePersonal  = dto.getAgreePersonal();
    }

}

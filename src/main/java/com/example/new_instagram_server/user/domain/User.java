package com.example.new_instagram_server.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long follow;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long following;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRoleType role;

    public User(String nickname, String password, String profileImageUrl, UserRoleType role) {
        this.nickname = nickname;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.role = role;
    }
}

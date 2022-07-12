package com.jaemin.triple.homework.User.entity;

import com.jaemin.triple.homework.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor()
@Entity
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private String userId;

    public User(String email, String name, String password, String userId) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userId = userId;
    }

    public static User of(String email, String name, String password, String userId) {
        return new User(email, name, password, userId);
    }
}

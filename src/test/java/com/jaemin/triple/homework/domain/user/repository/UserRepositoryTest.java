package com.jaemin.triple.homework.domain.user.repository;


import com.jaemin.triple.homework.User.entity.User;
import com.jaemin.triple.homework.User.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles(profiles = "test-container")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("유저 회원가입 테스트")
    @Test
    void UserSaveTest() {
        //given
        String userId = UUID.randomUUID().toString();

        User testUser = User.builder()
                .email("testA@gmail.com")
                .name("testA")
                .password("123456789")
                .userId(userId)
                .build();

        //when
        User savedUser = userRepository.save(testUser);

        //then
        assertThat(savedUser.getEmail()).isEqualTo("testA@gmail.com");
        assertThat(savedUser.getName()).isEqualTo("testA");
        assertThat(savedUser.getUserId()).isEqualTo(userId);
        assertThat(userRepository.findAll().size()).isEqualTo(1);
        assertThat(savedUser.getId()).isOne();
    }
}

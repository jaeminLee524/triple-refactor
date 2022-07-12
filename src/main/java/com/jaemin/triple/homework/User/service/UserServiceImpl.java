package com.jaemin.triple.homework.User.service;

import com.jaemin.triple.homework.User.dto.request.ReqUserDto;
import com.jaemin.triple.homework.User.entity.User;
import com.jaemin.triple.homework.User.exception.DuplicateUserEmail;
import com.jaemin.triple.homework.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public String createUser(ReqUserDto.RequestJoin requestJoin) {

        userRepository.deleteAllInBatch();

        if( userRepository.existsByEmail(requestJoin.getEmail()) ) {
            throw new DuplicateUserEmail(requestJoin.getEmail() + "은 중복된 이메일입니다.");
        }

        User createdUser = User.builder()
                .email(requestJoin.getEmail())
                .name(requestJoin.getName())
                .password(passwordEncoder.encode(requestJoin.getPassword()))
                .userId(UUID.randomUUID().toString())
                .build();

        User savedUser = userRepository.save(createdUser);

        return savedUser.getUserId();
    }
}

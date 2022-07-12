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

        /**
         * reafctor 1.
         * builder pattern -> 정적 팩토리 메소드
         * builder pattern은 null safe하지 않는다는 단점 => 변수가 추가되어도 컴파일 오류가 발생하지 않음
         * 보통 팀 컨벤션에 따라 사용한다고 함
        **/
        User createdUser = User.of(requestJoin.getEmail(), requestJoin.getEmail(),
                passwordEncoder.encode(requestJoin.getPassword()), UUID.randomUUID().toString());

        User savedUser = userRepository.save(createdUser);

        return savedUser.getUserId();
    }
}

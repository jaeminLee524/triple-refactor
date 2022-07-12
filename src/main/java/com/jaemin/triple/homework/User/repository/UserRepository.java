package com.jaemin.triple.homework.User.repository;

import com.jaemin.triple.homework.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    boolean existsByEmail(String email);
}

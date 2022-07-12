package com.jaemin.triple.homework.User.exception;

import com.jaemin.triple.homework.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicateUserEmail extends CustomException {

    public DuplicateUserEmail(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

package com.jaemin.triple.homework.Review.exception;

import com.jaemin.triple.homework.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ReviewException extends CustomException {

    public ReviewException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}

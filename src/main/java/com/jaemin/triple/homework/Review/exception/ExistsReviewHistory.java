package com.jaemin.triple.homework.Review.exception;

import com.jaemin.triple.homework.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExistsReviewHistory extends CustomException {
    public ExistsReviewHistory(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

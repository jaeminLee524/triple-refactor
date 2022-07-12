package com.jaemin.triple.homework.global.exception;

import com.jaemin.triple.homework.global.dto.Result;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private HttpStatus httpStatus;
    private Result errorResult;

    protected CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.errorResult = Result.createErrorResult(message);
    }
}

package com.jaemin.triple.homework.global.exception;

import com.jaemin.triple.homework.global.dto.Result;
import com.jaemin.triple.homework.global.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity customExceptionHandler(CustomException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        Result errorResult = exception.getErrorResult();

        log.warn("[CustomException] {}, {}", httpStatus, errorResult);

        return ResponseEntity.status(httpStatus).body(errorResult);
    }
}

package com.example.new_instagram_server.replies.advice;

import com.example.new_instagram_server.replies.advice.exception.NotFoundRepliesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RepliesExceptionHandler {
    @ExceptionHandler(NotFoundRepliesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundRepliesException() {
        return "답글이 존재하지 않습니다.";
    }
}

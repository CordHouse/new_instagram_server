package com.example.new_instagram_server.follow.advice;

import com.example.new_instagram_server.follow.advice.exception.AlreadyFollowException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FollowExceptionHandler {
    @ExceptionHandler(AlreadyFollowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyFollowException() {
        return "이미 팔로우 된 유저입니다.";
    }
}

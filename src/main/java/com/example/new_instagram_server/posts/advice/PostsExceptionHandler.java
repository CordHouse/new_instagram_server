package com.example.new_instagram_server.posts.advice;

import com.example.new_instagram_server.posts.advice.exception.NotFoundFeedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PostsExceptionHandler {
    @ExceptionHandler(NotFoundFeedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundFeedException() {
        return "피드가 존재하지 않습니다.";
    }
}

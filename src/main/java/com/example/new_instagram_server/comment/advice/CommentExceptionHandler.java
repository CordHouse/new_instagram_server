package com.example.new_instagram_server.comment.advice;

import com.example.new_instagram_server.comment.advice.exception.NotFoundCommentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExceptionHandler {
    @ExceptionHandler(NotFoundCommentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundCommentException() {
        return "댓글을 찾을 수 없습니다.";
    }
}

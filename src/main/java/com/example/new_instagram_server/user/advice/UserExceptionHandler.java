package com.example.new_instagram_server.user.advice;

import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.advice.exception.NotFoundUserException;
import com.example.new_instagram_server.user.advice.exception.PasswordMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(LoginTimeOutException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginTimeOutException() {
        return "다시 로그인해 주세요.";
    }

    @ExceptionHandler(PasswordMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordMismatchException() {
        return "비밀번호가 일치하지 않습니다.";
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundUserException() {
        return "회원이 존재하지 않습니다.";
    }
}

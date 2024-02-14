package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserSignInRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserLoginUseCase;
import com.example.new_instagram_server.user.application.port.in.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRegisterUseCase userRegisterUseCase; // UseCase 인터페이스를 의존
    private final UserLoginUseCase userLoginUseCase; // 로그인 인터페이스

    // 회원가입
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponseDto signUp(@ModelAttribute @Valid UserRegisterRequestDto userRegisterRequestDto) {
        return userRegisterUseCase.signUp(userRegisterRequestDto);
    }

    // 회원 탈퇴
    @DeleteMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestBody @Valid UserDeleteRequestDto userDeleteRequestDto) {
        userRegisterUseCase.deleteUser(userDeleteRequestDto);
    }

    // 로그인
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto signIn(@RequestBody @Valid UserSignInRequestDto userSignInRequestDto) {
        return userLoginUseCase.signIn(userSignInRequestDto);
    }
}
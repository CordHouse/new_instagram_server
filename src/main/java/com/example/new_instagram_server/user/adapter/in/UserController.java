package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.out.UserRegisterResponseDto;
import com.example.new_instagram_server.user.port.in.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRegisterUseCase userRegisterUseCase; // UseCase 인터페이스를 의존

    // 회원가입
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponseDto signUp(@ModelAttribute @Valid UserRegisterRequestDto userRegisterRequestDto) {
        return userRegisterUseCase.signUp(userRegisterRequestDto);
    }
}

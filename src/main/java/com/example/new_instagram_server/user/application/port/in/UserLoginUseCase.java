package com.example.new_instagram_server.user.application.port.in;

import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserSignInRequestDto;

public interface UserLoginUseCase {
    TokenResponseDto signIn(UserSignInRequestDto userSignInRequestDto);
}

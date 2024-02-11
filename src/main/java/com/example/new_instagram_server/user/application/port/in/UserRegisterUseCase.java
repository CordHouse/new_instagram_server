package com.example.new_instagram_server.user.application.port.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.UserRegisterResponseDto;

// 고수준 모듈과 저수준 모듈의 의존 역전을 위한 인터페이스 설계
public interface UserRegisterUseCase {
    UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto);
    void deleteUser(UserDeleteRequestDto userDeleteRequestDto);
}

package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserRegisterUseCase;
import com.example.new_instagram_server.user.application.port.service.UserRegisterService;
import lombok.RequiredArgsConstructor;

// 외부 시스템과 내부 시스템의 상호작용을 위한 어댑터
@RequiredArgsConstructor
public class UserRegisterUseCaseImpl implements UserRegisterUseCase {
    private final UserRegisterService userRegisterService; // UserRegisterUseCase 를 구현하고 있는 클래스를 의존
    @Override
    public UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto) {
        // 인터페이스를 의존
        return userRegisterService.signUp(userRegisterRequestDto);
    }

    @Override
    public void deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
        userRegisterService.deleteUser(userDeleteRequestDto);
    }
}

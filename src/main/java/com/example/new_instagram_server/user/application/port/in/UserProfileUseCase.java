package com.example.new_instagram_server.user.application.port.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;

public interface UserProfileUseCase {
    // 프로필 조회
    UserProfileResponseDto getProfile();
    // 프로필 수정
    UserEditProfileResponseDto editProfile(UserEditProfileRequestDto userEditProfileRequestDto);
}

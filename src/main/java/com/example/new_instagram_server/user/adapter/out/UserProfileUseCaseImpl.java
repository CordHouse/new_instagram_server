package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserProfileUseCase;
import com.example.new_instagram_server.user.application.port.service.UserProfileService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserProfileUseCaseImpl implements UserProfileUseCase {
    private final UserProfileService userProfileService;

    // 프로필 조회
    @Override
    public UserProfileResponseDto getProfile() {
        return userProfileService.getProfile();
    }

    // 프로필 수정
    @Override
    public UserEditProfileResponseDto editProfile(UserEditProfileRequestDto userEditProfileRequestDto) {
        return userProfileService.editProfile(userEditProfileRequestDto);
    }
}

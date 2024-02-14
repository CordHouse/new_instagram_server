package com.example.new_instagram_server.user.application.port.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;

public interface UserProfileUseCase {
    UserProfileResponseDto getProfile(UserProfileRequestDto userProfileRequestDto);
    UserEditProfileResponseDto editProfile(UserEditProfileRequestDto userEditProfileRequestDto);
}

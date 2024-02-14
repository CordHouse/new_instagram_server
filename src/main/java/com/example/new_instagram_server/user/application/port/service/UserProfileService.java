package com.example.new_instagram_server.user.application.port.service;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;
import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.application.port.in.UserProfileUseCase;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService implements UserProfileUseCase {
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;

    // 프로필 조회
    @Override
    public UserProfileResponseDto getProfile() {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(() -> {
            throw new LoginTimeOutException();
        });
        return new UserProfileResponseDto().toDo(user);
    }

    // 프로필 수정
    @Override
    public UserEditProfileResponseDto editProfile(UserEditProfileRequestDto userEditProfileRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(() -> {
            throw new LoginTimeOutException();
        });

        user.setNickname(userEditProfileRequestDto.getNickname());
        user.setProfileImageUrl(userEditProfileRequestDto.getProfileImageUrl().getOriginalFilename());
        return new UserEditProfileResponseDto().toDo(user);
    }
}

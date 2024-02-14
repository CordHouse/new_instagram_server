package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileUseCase userProfileUseCase;

    // 프로필 조회
    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponseDto getProfile() {
        return userProfileUseCase.getProfile();
    }

    // 프로필 수정
    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public UserEditProfileResponseDto editProfile(
            @ModelAttribute @Valid UserEditProfileRequestDto userEditProfileRequestDto) {
        return userProfileUseCase.editProfile(userEditProfileRequestDto);
    }
}

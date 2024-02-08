package com.example.new_instagram_server.user.port.service;

import com.example.new_instagram_server.user.adapter.in.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.UserRegisterResponseDto;
import com.example.new_instagram_server.user.domain.User;
import com.example.new_instagram_server.user.domain.UserRoleType;
import com.example.new_instagram_server.user.port.in.UserRegisterUseCase;
import com.example.new_instagram_server.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 비지니스 로직 설계
@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserRepository userRepository; // 데이터베이스를 상호작용하는 인터페이스를 의존

    // 회원가입 로직 설계
    @Override
    public UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = User.builder()
                .nickname(userRegisterRequestDto.getNickname())
                .profile_image_url(userRegisterRequestDto.getProfile_image().getOriginalFilename())
                .role(UserRoleType.ROLE_MEMBER)
                .build();
        userRepository.save(newUser);
        return new UserRegisterResponseDto().toDo(newUser);
    }
}

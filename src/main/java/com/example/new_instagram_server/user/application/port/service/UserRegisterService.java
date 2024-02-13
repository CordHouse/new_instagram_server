package com.example.new_instagram_server.user.application.port.service;

import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.advice.exception.PasswordMismatchException;
import com.example.new_instagram_server.user.application.port.in.UserRegisterUseCase;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.PasswordEncoder;
import com.example.new_instagram_server.user.domain.User;
import com.example.new_instagram_server.user.domain.UserRoleType;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 비지니스 로직 설계
@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserRepository userRepository; // 데이터베이스를 상호작용하는 인터페이스를 의존
    private final GetAuthentication getAuthentication; // 토큰정보 상호작용
    private final PasswordEncoder passwordEncoder;

    // 회원가입 로직 설계
    @Override
    public UserRegisterResponseDto signUp(UserRegisterRequestDto userRegisterRequestDto) {
        User newUser = User.builder()
                .nickname(userRegisterRequestDto.getNickname())
                .password(passwordEncoder.encoder(userRegisterRequestDto.getPassword()))
                .profile_image_url(userRegisterRequestDto.getProfile_image().getOriginalFilename())
                .role(UserRoleType.ROLE_MEMBER)
                .build();
        userRepository.save(newUser);
        return new UserRegisterResponseDto().toDo(newUser);
    }

    // 회원 탈퇴 로직 설계
    @Override
    public void deleteUser(UserDeleteRequestDto userDeleteRequestDto) {
        // 입력된 두 비밀번호가 같은지 확인한다.
        if(!userDeleteRequestDto.getPassword().equals(userDeleteRequestDto.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }
        // 현재 로그인한 아이디 정보를 가져온다.
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName()).orElseThrow(() -> {
            throw new LoginTimeOutException();
        });
        // 계정 삭제를 위해 입력한 비밀번호와 계정의 비밀번호가 맞는지 비교한다.
        // 입력 받은 패스워드, 데이터베이스에 암호화된 비밀번호의 일치여부 확인
        if(!passwordEncoder.matches(userDeleteRequestDto.getPassword(), user.getPassword())) {
            throw new PasswordMismatchException();
        }
        // 비밀번호가 일치한다면 삭제를 진행한다.
        userRepository.delete(user);
    }
}

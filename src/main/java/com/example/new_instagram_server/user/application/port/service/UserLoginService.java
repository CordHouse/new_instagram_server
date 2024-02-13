package com.example.new_instagram_server.user.application.port.service;

import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserSignInRequestDto;
import com.example.new_instagram_server.user.advice.exception.NotFoundUserException;
import com.example.new_instagram_server.user.advice.exception.PasswordMismatchException;
import com.example.new_instagram_server.user.application.port.in.UserLoginUseCase;
import com.example.new_instagram_server.user.application.port.out.AuthenticationManager;
import com.example.new_instagram_server.user.application.port.out.PasswordEncoder;
import com.example.new_instagram_server.user.application.port.out.TokenInfo;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService implements UserLoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenInfo tokenInfo;

    // 로그인
    @Override
    public TokenResponseDto signIn(UserSignInRequestDto userSignInRequestDto) {
        // 아이디 확인
        User signInUser = userRepository.findByNickname(userSignInRequestDto.getNickname()).orElseThrow(() -> {
            throw new NotFoundUserException();
        });
        // 비밀번호 확인
        if(!passwordEncoder.matches(userSignInRequestDto.getPassword(), signInUser.getPassword())) {
            throw new PasswordMismatchException();
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userSignInRequestDto.getNickname(),
                userSignInRequestDto.getPassword()
        );
        Authentication authentication = authenticationManager.getAuthentication(authenticationToken);

        return tokenInfo.createToken(authentication, signInUser.getId());
    }
}

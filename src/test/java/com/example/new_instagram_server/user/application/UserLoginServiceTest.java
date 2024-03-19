package com.example.new_instagram_server.user.application;

import com.example.new_instagram_server.user.adapter.in.dto.UserSignInRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.application.port.out.AuthenticationManager;
import com.example.new_instagram_server.user.application.port.out.PasswordEncoder;
import com.example.new_instagram_server.user.application.port.out.TokenInfo;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.application.port.service.UserLoginService;
import com.example.new_instagram_server.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserLoginServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenInfo tokenInfo;

    @InjectMocks
    private UserLoginService userLoginService;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("로그인 정상처리")
    void signInValidRequestReturnOk() {
        // given
        UserSignInRequestDto requestDto = new UserSignInRequestDto();
        requestDto.setNickname("test");
        requestDto.setPassword("1234");

        User signInUser = new User();

        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken("access");
        responseDto.setAccessToken("refresh");

        String savePassword = passwordEncoder.encoder(requestDto.getPassword());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                requestDto.getNickname(),
                requestDto.getPassword()
        );

        doReturn(Optional.of(signInUser)).when(userRepository).findByNickname(requestDto.getNickname());
        doReturn(true).when(passwordEncoder).matches(eq(requestDto.getPassword()), any());
        doReturn(responseDto).when(tokenInfo).createToken(any(), eq(signInUser.getId()));

        // when
        TokenResponseDto userToken = userLoginService.signIn(requestDto);

        // then
        assertThat(userToken.getAccessToken()).isEqualTo(responseDto.getAccessToken());
        assertThat(userToken.getRefreshToken()).isEqualTo(responseDto.getRefreshToken());

        assertThat(passwordEncoder.matches(requestDto.getPassword(), savePassword)).isTrue();
        verify(userRepository, times(1)).findByNickname(requestDto.getNickname());
        verify(passwordEncoder, times(2)).matches(requestDto.getPassword(), savePassword);
        verify(authenticationManager, times(1)).getAuthentication(token);
    }
}

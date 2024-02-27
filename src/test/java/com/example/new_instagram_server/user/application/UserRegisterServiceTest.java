package com.example.new_instagram_server.user.application;

import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.PasswordEncoder;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.application.port.service.UserRegisterService;
import com.example.new_instagram_server.user.domain.User;
import com.example.new_instagram_server.user.domain.UserRoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRegisterServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private GetAuthentication getAuthentication;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegisterService userRegisterService;

    @Test
    @DisplayName("회원가입")
    void signUpReturnOk() {
        // given
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        requestDto.setNickname("test");
        requestDto.setPassword("1234");
        requestDto.setProfileImage(new MockMultipartFile(
                "name",
                "name.png",
                "image/png",
                "name.png".getBytes()
        ));

        User newUser = new User();
        newUser.setNickname("test");
        newUser.setPassword(passwordEncoder.encoder("1234"));
        newUser.setProfileImageUrl("name.png");
        newUser.setRole(UserRoleType.ROLE_MEMBER);

        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setId(1L);
        responseDto.setNickname("test");
        responseDto.setProfileImageUrl("name.png");

        doNothing().when(userRepository).save(newUser);

        // when
        UserRegisterResponseDto registerResponseDto = userRegisterService.signUp(requestDto);

        // then
        assertThat(registerResponseDto).isNotNull();
        assertThat(registerResponseDto.getNickname()).isEqualTo("test");
        assertThat(registerResponseDto.getProfileImageUrl()).isEqualTo("name.png");
    }

    @Test
    @DisplayName("회원탈퇴")
    void deleteUserReturnOk() {
        // given
        UserDeleteRequestDto requestDto = new UserDeleteRequestDto();
        requestDto.setPassword("1234");
        requestDto.setConfirmPassword("1234");

        User user = new User();
        user.setPassword(passwordEncoder.encoder("1234"));

        Authentication authentication = mock(Authentication.class);
        doReturn("test").when(authentication).getName();
        doReturn(authentication).when(getAuthentication).getAuthentication();
        doReturn(Optional.of(user)).when(userRepository).findByNickname(anyString());
        doReturn(true).when(passwordEncoder).matches(
                eq(requestDto.getPassword()), any()
        );

        // when
        userRegisterService.deleteUser(requestDto);

        // then
        assertThat(requestDto.getPassword()).isEqualTo(requestDto.getConfirmPassword());
        verify(userRepository, times(1)).findByNickname(
                getAuthentication.getAuthentication().getName()
        );
        verify(passwordEncoder, times(1)).matches(
                requestDto.getPassword(), user.getPassword()
        );
        verify(userRepository, times(1)).delete(user);
    }
}

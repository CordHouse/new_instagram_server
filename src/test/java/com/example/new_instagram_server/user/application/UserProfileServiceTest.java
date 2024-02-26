package com.example.new_instagram_server.user.application;

import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.application.port.service.UserProfileService;
import com.example.new_instagram_server.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private GetAuthentication getAuthentication;

    @InjectMocks
    private UserProfileService userProfileService;

    @Test
    @DisplayName("프로필 조회")
    void getProfileRequestReturnOk() {
        // given
        UserProfileResponseDto responseDto = new UserProfileResponseDto();
        responseDto.setNickname("test");

        User user = new User();
        user.setNickname("test");

        // getAuthentication 목 객체 생성
        Authentication authentication = mock(Authentication.class);
        doReturn(responseDto.getNickname()).when(authentication).getName();
        doReturn(authentication).when(getAuthentication).getAuthentication();
        doReturn(Optional.of(user)).when(userRepository).findByNickname(anyString());

        // when
        UserProfileResponseDto profileResponseDto = userProfileService.getProfile();

        // then
        assertThat(profileResponseDto).isNotNull();
        assertThat(profileResponseDto.getNickname()).isEqualTo(responseDto.getNickname());
    }

    @Test
    @DisplayName("프로필 수정")
    void editProfileRequestReturnOk() {
        // given

        // when

        // then
    }
}

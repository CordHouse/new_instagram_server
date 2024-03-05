package com.example.new_instagram_server.follow.application;

import com.example.new_instagram_server.follow.adapter.in.dto.UserFollowRequestDto;
import com.example.new_instagram_server.follow.adapter.in.dto.UserUnFollowRequestDto;
import com.example.new_instagram_server.follow.application.port.out.FollowRepository;
import com.example.new_instagram_server.follow.application.port.service.FollowService;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FollowServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private FollowRepository followRepository;

    @Mock
    private GetAuthentication getAuthentication;

    @InjectMocks
    private FollowService followService;

    @Test
    @DisplayName("팔로우 신청 비즈니스 로직")
    void userFollowServiceReturnOk() {
        // given
        UserFollowRequestDto requestDto = new UserFollowRequestDto();
        requestDto.setReceiveUserId(1L);

        User senderUser = new User();
        senderUser.setNickname("sender");
        senderUser.setId(2L);
        senderUser.setFollow(0L);

        User receiverUser = new User();
        receiverUser.setNickname("receiver");
        receiverUser.setId(1L);
        receiverUser.setFollowing(0L);

        Authentication authentication = mock(Authentication.class);
        doReturn(senderUser.getNickname()).when(authentication).getName();
        doReturn(authentication).when(getAuthentication).getAuthentication();

        doReturn(Optional.of(senderUser)).when(userRepository).findByNickname(anyString());
        doReturn(Optional.of(receiverUser)).when(userRepository).findById(receiverUser.getId());

        // when
        followService.userFollow(requestDto);

        // then
        assertThat(senderUser.getFollow()).isEqualTo(1L);
        assertThat(receiverUser.getFollowing()).isEqualTo(1L);
    }

    @Test
    @DisplayName("팔로우 취소 비즈니스 로직")
    void userUnFollowServiceReturnOk() {
        // given
        UserUnFollowRequestDto requestDto = new UserUnFollowRequestDto();
        requestDto.setUnFollowUserId(1L);

        User senderUser = new User();
        senderUser.setNickname("sender");
        senderUser.setId(2L);
        senderUser.setFollow(1L);

        User receiverUser = new User();
        receiverUser.setNickname("receiver");
        receiverUser.setId(1L);
        receiverUser.setFollowing(1L);

        Authentication authentication = mock(Authentication.class);
        doReturn(senderUser.getNickname()).when(authentication).getName();
        doReturn(authentication).when(getAuthentication).getAuthentication();

        doReturn(Optional.of(senderUser)).when(userRepository).findByNickname(anyString());
        doReturn(Optional.of(receiverUser)).when(userRepository).findById(receiverUser.getId());

        // when
        followService.userUnFollow(requestDto);

        // then
        assertThat(senderUser.getFollow()).isEqualTo(0L);
        assertThat(receiverUser.getFollowing()).isEqualTo(0L);
    }
}

package com.example.new_instagram_server.follow.adapter.in;

import com.example.new_instagram_server.follow.adapter.in.dto.UserFollowRequestDto;
import com.example.new_instagram_server.follow.adapter.in.dto.UserUnFollowRequestDto;
import com.example.new_instagram_server.follow.application.port.in.FollowUseCase;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FollowControllerTest {
    @Mock
    private FollowUseCase followUseCase;

    @InjectMocks
    private FollowController followController;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
    }

    @Test
    @DisplayName("팔로우 신청")
    void userFollowReturnOk() throws Exception {
        // given
        UserFollowRequestDto requestDto = new UserFollowRequestDto();
        requestDto.setReceiveUserId(1L);

        doNothing().when(followUseCase).userFollow(requestDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/user/follow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(requestDto))
        );

        // then
        resultActions.andExpect(status().isOk());
        verify(followUseCase, times(1)).userFollow(requestDto);
    }

    @Test
    @DisplayName("팔로우 취소")
    void userUnFollowReturnOk() throws Exception {
        // given
        UserUnFollowRequestDto requestDto = new UserUnFollowRequestDto();
        requestDto.setUnFollowUserId(1L);

        doNothing().when(followUseCase).userUnFollow(requestDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                delete("/user/follow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(requestDto))
        );

        // then
        resultActions.andExpect(status().isOk());
        verify(followUseCase, times(1)).userUnFollow(requestDto);
    }
}

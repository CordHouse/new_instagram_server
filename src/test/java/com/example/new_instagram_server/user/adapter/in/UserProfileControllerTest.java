package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserEditProfileRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserEditProfileResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserProfileResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserProfileUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {
    @Mock
    private UserProfileUseCase userProfileUseCase;

    @InjectMocks
    private UserProfileController userProfileController;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
    }

    @Test
    @DisplayName("프로필 조회")
    void getProfileValidRequestReturnOk() throws Exception {
        // given
        UserProfileResponseDto responseDto = new UserProfileResponseDto();

        doReturn(responseDto).when(userProfileUseCase).getProfile();
        // when
        ResultActions resultActions = mockMvc.perform(
                get("/user/profile")
        );

        // then
        resultActions.andExpect(status().isOk());
        verify(userProfileUseCase, times(1)).getProfile();
    }

    @Test
    @DisplayName("프로필 수정")
    void editProfileValidRequestReturnOk() throws Exception {
        // given
        UserEditProfileRequestDto requestDto = new UserEditProfileRequestDto();
        requestDto.setNickname("test");
        requestDto.setProfileImageUrl(new MockMultipartFile(
                "name",
                "name.png",
                "image/png",
                "name.png".getBytes()
        ));
        UserEditProfileResponseDto responseDto = new UserEditProfileResponseDto();
        responseDto.setId(1L);
        responseDto.setNickname("test");
        responseDto.setProfileImageUrl("name.png");

        doReturn(responseDto).when(userProfileUseCase).editProfile(any(UserEditProfileRequestDto.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                multipart(HttpMethod.PUT, "/user/profile")
                        .file("profileImageUrl", requestDto.getProfileImageUrl().getBytes())
                        .param("nickname", requestDto.getNickname())
        );

        UserEditProfileResponseDto userEditProfileResponseDto = userProfileUseCase.editProfile(requestDto);

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("nickname", responseDto.getNickname()).exists())
                .andExpect(jsonPath("profileImageUrl", responseDto.getProfileImageUrl()).exists())
                .andReturn();
        verify(userProfileUseCase, times(1)).editProfile(requestDto);
        assertEquals(responseDto, userEditProfileResponseDto);
    }
}

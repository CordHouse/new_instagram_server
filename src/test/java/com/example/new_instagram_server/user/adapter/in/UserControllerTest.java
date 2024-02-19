package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserDeleteRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.in.dto.UserSignInRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserLoginUseCase;
import com.example.new_instagram_server.user.application.port.in.UserRegisterUseCase;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserRegisterUseCase userRegisterUseCase;

    @Mock
    private UserLoginUseCase userLoginUseCase;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc; // Controller 에서 HTTP 를 호출하는 것을 테스트 하기 위해 사용한다.

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void signUpValidRequestReturnOk() throws Exception {
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        requestDto.setNickname("test");
        requestDto.setPassword("1234");
        requestDto.setProfileImage(new MockMultipartFile(
                "name",
                "name.png",
                "image/png",
                "name.png".getBytes()
        ));
        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        responseDto.setId(1L);
        responseDto.setNickname("test");
        responseDto.setProfileImageUrl("name.png");
        doReturn(responseDto).when(userRegisterUseCase).signUp(any(UserRegisterRequestDto.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                multipart("/user/sign-up")
                        .file("profileImage", requestDto.getProfileImage().getBytes())
                        .param("nickname", requestDto.getNickname())
                        .param("password", requestDto.getPassword())
                );

        UserRegisterResponseDto userResponseDto = userController.signUp(requestDto);

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("nickname", responseDto.getNickname()).exists())
                .andExpect(jsonPath("profileImageUrl", responseDto.getProfileImageUrl()).exists());

        verify(userRegisterUseCase, times(1)).signUp(requestDto); // 1번 검증 -> 확실한 지칭
        verify(userRegisterUseCase, times(2)).signUp(any(UserRegisterRequestDto.class)); // 2번 검증 -> 어떤 걸로 호출하던 양식만 맞으면 ok
        assertEquals(userResponseDto, responseDto);
    }

    @Test
    @DisplayName("회원 탈퇴")
    void deleteUserValidRequestReturnOk() throws Exception {
        // given
        UserDeleteRequestDto requestDto = new UserDeleteRequestDto();
        requestDto.setPassword("1234");
        requestDto.setConfirmPassword("1234");
        doNothing().when(userRegisterUseCase).deleteUser(any(UserDeleteRequestDto.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                delete("/user/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(requestDto))
        );

        // then
        resultActions.andExpect(status().isOk());
        verify(userRegisterUseCase, times(1)).deleteUser(requestDto);
    }

    @Test
    @DisplayName("로그인")
    void signInValidRequestReturnOk() throws Exception {
        // given
        UserSignInRequestDto requestDto = new UserSignInRequestDto();
        requestDto.setNickname("test");
        requestDto.setPassword("1234");

        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken("testAccessToken");
        responseDto.setRefreshToken("testRefreshToken");
        doReturn(responseDto).when(userLoginUseCase).signIn(any(UserSignInRequestDto.class));

        // when
        ResultActions resultActions = mockMvc.perform(
                post("/user/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(requestDto))
        );

        TokenResponseDto tokenResponseDto = userController.signIn(requestDto);

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("accessToken", responseDto.getAccessToken()).exists())
                .andExpect(jsonPath("refreshToken", responseDto.getRefreshToken()).exists());
        verify(userLoginUseCase, times(2)).signIn(requestDto);
        assertEquals(responseDto, tokenResponseDto);
    }
}

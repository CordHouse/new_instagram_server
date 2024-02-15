package com.example.new_instagram_server.user.adapter.in;

import com.example.new_instagram_server.user.adapter.in.dto.UserRegisterRequestDto;
import com.example.new_instagram_server.user.adapter.out.dto.UserRegisterResponseDto;
import com.example.new_instagram_server.user.application.port.in.UserLoginUseCase;
import com.example.new_instagram_server.user.application.port.in.UserRegisterUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    @Mock
    private UserRegisterUseCase userRegisterUseCase;

    @Mock
    private UserLoginUseCase userLoginUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void signUpValidRequestReturnOk() {
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();
        when(userRegisterUseCase.signUp(any())).thenReturn(new UserRegisterResponseDto());

        UserRegisterResponseDto responseDtoMock = userController.signUp(requestDto);

        verify(userRegisterUseCase, times(1)).signUp(requestDto);
        assertEquals(responseDto, responseDtoMock);
    }
}

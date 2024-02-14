package com.example.new_instagram_server.user.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    @NotBlank(message = "사용할 아이디를 입력해주세요.")
    private String nickname;

    @NotBlank(message = "사용할 비밀번호를 입력해주세요.")
    private String password;

    private MultipartFile profile_image;
}

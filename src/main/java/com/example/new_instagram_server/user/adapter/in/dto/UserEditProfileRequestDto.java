package com.example.new_instagram_server.user.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditProfileRequestDto {
    @NotBlank(message = "수정할 유저 닉네임을 입력해주세요.")
    private String nickname;

    private MultipartFile profileImageUrl;
}

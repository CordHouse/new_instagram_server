package com.example.new_instagram_server.user.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDeleteRequestDto {
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 다시 한 번 입력해주세요.")
    private String confirmPassword;
}

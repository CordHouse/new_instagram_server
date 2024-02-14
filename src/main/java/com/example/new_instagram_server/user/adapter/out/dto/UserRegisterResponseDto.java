package com.example.new_instagram_server.user.adapter.out.dto;

import com.example.new_instagram_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDto {
    private long id;
    private String nickname;
    private String profile_image_url;

    public UserRegisterResponseDto toDo(User user) {
        return new UserRegisterResponseDto(user.getId(), user.getNickname(), user.getProfile_image_url());
    }
}

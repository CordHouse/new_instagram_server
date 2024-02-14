package com.example.new_instagram_server.user.adapter.out.dto;

import com.example.new_instagram_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditProfileResponseDto {
    private long id;
    private String nickname;
    private String profileImageUrl;

    public UserEditProfileResponseDto toDo(User user) {
        return new UserEditProfileResponseDto(user.getId(), user.getNickname(), user.getProfileImageUrl());
    }
}

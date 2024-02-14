package com.example.new_instagram_server.user.adapter.out.dto;

import com.example.new_instagram_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {
    private String nickname;
    private String profileImageUrl;
    private long follow;
    private long following;

    public UserProfileResponseDto toDo(User user) {
        return new UserProfileResponseDto(
                user.getNickname(),
                user.getProfileImageUrl(),
                user.getFollow(),
                user.getFollowing()
        );
    }
}

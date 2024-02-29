package com.example.new_instagram_server.follow.application.port.in;

import com.example.new_instagram_server.follow.adapter.in.dto.UserFollowRequestDto;
import com.example.new_instagram_server.follow.adapter.in.dto.UserUnFollowRequestDto;

public interface FollowUseCase {
    void userFollow(UserFollowRequestDto userFollowRequestDto);

    void userUnFollow(UserUnFollowRequestDto userUnFollowRequestDto);
}

package com.example.new_instagram_server.follow.application.port.out;

import com.example.new_instagram_server.follow.domain.Follow;
import com.example.new_instagram_server.user.domain.User;

public interface FollowRepository {
    void deleteByReceiver(User receiver);

    Follow findBySenderAndReceiver(User sender, User receiver);

    void save(Follow follow);
}

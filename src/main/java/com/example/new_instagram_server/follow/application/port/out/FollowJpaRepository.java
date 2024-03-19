package com.example.new_instagram_server.follow.application.port.out;

import com.example.new_instagram_server.follow.domain.Follow;
import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<Follow, Long> {
    void deleteByReceiver(User receiver);

    Follow findBySenderAndReceiver(User sender, User receiver);
}

package com.example.new_instagram_server.follow.adapter.out;

import com.example.new_instagram_server.follow.application.port.out.FollowJpaRepository;
import com.example.new_instagram_server.follow.application.port.out.FollowRepository;
import com.example.new_instagram_server.follow.domain.Follow;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {
    private final FollowJpaRepository jpaRepository;
    @Override
    public void deleteByReceiver(User receiver) {
        jpaRepository.deleteByReceiver(receiver);
    }

    @Override
    public Follow findBySenderAndReceiver(User sender, User receiver) {
        return jpaRepository.findBySenderAndReceiver(sender, receiver);
    }

    @Override
    public void save(Follow follow) {
        jpaRepository.save(follow);
    }
}

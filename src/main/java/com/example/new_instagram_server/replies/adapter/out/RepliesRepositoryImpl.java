package com.example.new_instagram_server.replies.adapter.out;

import com.example.new_instagram_server.replies.application.port.out.RepliesJpaRepository;
import com.example.new_instagram_server.replies.application.port.out.RepliesRepository;
import com.example.new_instagram_server.replies.domain.Replies;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RepliesRepositoryImpl implements RepliesRepository {
    private final RepliesJpaRepository repliesJpaRepository;
    @Override
    public void save(Replies replies) {
        repliesJpaRepository.save(replies);
    }

    @Override
    public Optional<Replies> findByIdAndUser(long id, User user) {
        return repliesJpaRepository.findByIdAndUser(id, user);
    }

    @Override
    public void deleteByIdAndUser(long id, User user) {
        repliesJpaRepository.deleteByIdAndUser(id, user);
    }
}

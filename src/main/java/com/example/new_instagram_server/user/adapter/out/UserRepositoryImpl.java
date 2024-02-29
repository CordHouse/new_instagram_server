package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.application.port.out.UserJpaRepository;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// UserRepository 를 구현하는 구현체이다.
// User 데이터베이스와 연관된 작업을 여기서 처리한다.
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public void save(User user) {
        jpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return jpaRepository.findByNickname(nickname);
    }

    @Override
    public void delete(User user) {
        jpaRepository.delete(user);
    }
}

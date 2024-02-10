package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.application.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

// UserRepository 를 구현하는 구현체이다.
// User 데이터베이스와 연관된 작업을 여기서 처리한다.
@RequiredArgsConstructor
public class UserRepositoryImpl<T> implements UserRepository<T> {
    private final JpaRepository<T, Long> jpaRepository;

    @Override
    public void save(T entity) {
        jpaRepository.save(entity);
    }
}

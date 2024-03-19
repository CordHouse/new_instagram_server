package com.example.new_instagram_server.replies.application.port.out;

import com.example.new_instagram_server.replies.domain.Replies;
import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepliesJpaRepository extends JpaRepository<Replies, Long> {
    // 아이디와 유저가 일치하는 값이 있는지 찾는다.
    Optional<Replies> findByIdAndUser(long id, User user);
    // 아이디와 유저값이 일치하는 값을 삭제한다.
    void deleteByIdAndUser(long id, User user);
}

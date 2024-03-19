package com.example.new_instagram_server.replies.application.port.out;

import com.example.new_instagram_server.replies.domain.Replies;
import com.example.new_instagram_server.user.domain.User;

import java.util.Optional;

public interface RepliesRepository {
    // 저장
    void save(Replies replies);
    // 아이디와 유저가 일치하는 값이 있는지 찾는다.
    Optional<Replies> findByIdAndUser(long id, User user);
    // 아이디와 유저값이 일치하는 값을 삭제한다.
    void deleteByIdAndUser(long id, User user);
}

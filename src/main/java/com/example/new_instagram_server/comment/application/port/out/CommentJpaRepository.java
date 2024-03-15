package com.example.new_instagram_server.comment.application.port.out;

import com.example.new_instagram_server.comment.domain.Comment;
import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(long commentId, User user);
    void deleteByIdAndUser(long commentId, User user);
}

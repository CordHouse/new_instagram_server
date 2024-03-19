package com.example.new_instagram_server.comment.application.port.out;

import com.example.new_instagram_server.comment.domain.Comment;
import com.example.new_instagram_server.user.domain.User;

import java.util.Optional;

public interface CommentRepository {
    void save(Comment comment);

    Optional<Comment> findByIdAndUser(long commentId, User user);

    void deleteByIdAndUser(long commentId, User user);

    Optional<Comment> findById(long id);
}

package com.example.new_instagram_server.comment.adapter.out;

import com.example.new_instagram_server.comment.application.port.out.CommentJpaRepository;
import com.example.new_instagram_server.comment.application.port.out.CommentRepository;
import com.example.new_instagram_server.comment.domain.Comment;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    private final CommentJpaRepository commentJpaRepository;
    @Override
    public void save(Comment comment) {
        commentJpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findByIdAndUser(long commentId, User user) {
        return commentJpaRepository.findByIdAndUser(commentId, user);
    }

    @Override
    public void deleteByIdAndUser(long commentId, User user) {
        commentJpaRepository.deleteByIdAndUser(commentId, user);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return commentJpaRepository.findById(id);
    }
}

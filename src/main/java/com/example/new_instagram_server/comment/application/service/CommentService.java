package com.example.new_instagram_server.comment.application.service;

import com.example.new_instagram_server.comment.adapter.in.dto.CommentCreateRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentDeleteRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentEditRequestDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentCreateResponseDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentEditResponseDto;
import com.example.new_instagram_server.comment.advice.exception.NotFoundCommentException;
import com.example.new_instagram_server.comment.application.port.in.CommentUseCase;
import com.example.new_instagram_server.comment.application.port.out.CommentRepository;
import com.example.new_instagram_server.comment.domain.Comment;
import com.example.new_instagram_server.posts.application.port.out.PostsRepository;
import com.example.new_instagram_server.posts.domain.Posts;
import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService implements CommentUseCase {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final GetAuthentication getAuthentication;

    // 댓글 생성
    @Override
    public CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);

        Posts posts = postsRepository.findById(commentCreateRequestDto.getPostId())
                .orElseThrow();
        Comment comment = new Comment(
                user,
                posts,
                commentCreateRequestDto.getContent()
        );
        commentRepository.save(comment);
        return new CommentCreateResponseDto().toDo(comment);
    }

    // 댓글 수정
    @Override
    public CommentEditResponseDto editComment(CommentEditRequestDto commentEditRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);

        Comment editComment = commentRepository.findByIdAndUser(commentEditRequestDto.getCommentId(), user)
                .orElseThrow(NotFoundCommentException::new);
        editComment.setContent(commentEditRequestDto.getContent());
        return new CommentEditResponseDto().toDo(editComment);
    }

    // 댓글 삭제
    @Override
    public void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        commentRepository.deleteByIdAndUser(commentDeleteRequestDto.getCommentId(), user);
    }
}

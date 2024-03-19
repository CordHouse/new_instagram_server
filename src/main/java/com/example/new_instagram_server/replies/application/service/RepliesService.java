package com.example.new_instagram_server.replies.application.service;

import com.example.new_instagram_server.comment.advice.exception.NotFoundCommentException;
import com.example.new_instagram_server.comment.application.port.out.CommentRepository;
import com.example.new_instagram_server.comment.domain.Comment;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesCreateRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesDeleteRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesEditRequestDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesCreateResponseDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesEditResponseDto;
import com.example.new_instagram_server.replies.advice.exception.NotFoundRepliesException;
import com.example.new_instagram_server.replies.application.port.in.RepliesUseCase;
import com.example.new_instagram_server.replies.application.port.out.RepliesRepository;
import com.example.new_instagram_server.replies.domain.Replies;
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
public class RepliesService implements RepliesUseCase {
    private final RepliesRepository repliesRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;

    @Transactional
    public RepliesCreateResponseDto createReplies(RepliesCreateRequestDto repliesCreateRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        Comment choiceComment = commentRepository.findById(repliesCreateRequestDto.getCommentId())
                .orElseThrow(NotFoundCommentException::new);
        Replies newReplies = new Replies(
                user,
                choiceComment,
                repliesCreateRequestDto.getContent()
        );
        repliesRepository.save(newReplies);
        return new RepliesCreateResponseDto().toDo(newReplies);
    }

    // 답글 수정
    @Transactional
    public RepliesEditResponseDto editReplies(RepliesEditRequestDto repliesEditRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        Replies editReplies = repliesRepository.findByIdAndUser(repliesEditRequestDto.getCommentId(), user)
                .orElseThrow(NotFoundRepliesException::new);
        editReplies.setContent(repliesEditRequestDto.getContent());
        return new RepliesEditResponseDto().toDo(editReplies);
    }

    // 답글 삭제
    @Transactional
    public void deleteReplies(RepliesDeleteRequestDto repliesDeleteRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        repliesRepository.deleteByIdAndUser(repliesDeleteRequestDto.getRepliesId(), user);
    }
}

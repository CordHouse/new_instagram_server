package com.example.new_instagram_server.comment.adapter.in;

import com.example.new_instagram_server.comment.adapter.in.dto.CommentCreateRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentDeleteRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentEditRequestDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentCreateResponseDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentEditResponseDto;
import com.example.new_instagram_server.comment.application.port.in.CommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/posts/comment")
public class CommentController {
    private final CommentUseCase commentUseCase;

    // 댓글 생성
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public CommentCreateResponseDto createComment(@RequestBody @Valid CommentCreateRequestDto commentCreateRequestDto) {
        return commentUseCase.createComment(commentCreateRequestDto);
    }

    // 댓글 수정
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public CommentEditResponseDto editComment(@RequestBody @Valid CommentEditRequestDto commentEditRequestDto) {
        return commentUseCase.editComment(commentEditRequestDto);
    }

    // 댓글 삭제
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@RequestBody @Valid CommentDeleteRequestDto commentDeleteRequestDto) {
        commentUseCase.deleteComment(commentDeleteRequestDto);
    }

}

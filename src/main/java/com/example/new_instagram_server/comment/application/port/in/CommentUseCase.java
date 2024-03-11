package com.example.new_instagram_server.comment.application.port.in;

import com.example.new_instagram_server.comment.adapter.in.dto.CommentCreateRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentDeleteRequestDto;
import com.example.new_instagram_server.comment.adapter.in.dto.CommentEditRequestDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentCreateResponseDto;
import com.example.new_instagram_server.comment.adapter.out.dto.CommentEditResponseDto;

public interface CommentUseCase {
    // 생성
    CommentCreateResponseDto createComment(CommentCreateRequestDto commentCreateRequestDto);
    // 수정
    CommentEditResponseDto editComment(CommentEditRequestDto commentEditRequestDto);
    // 삭제
    void deleteComment(CommentDeleteRequestDto commentDeleteRequestDto);
}

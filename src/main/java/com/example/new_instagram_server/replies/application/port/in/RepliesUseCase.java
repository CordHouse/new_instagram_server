package com.example.new_instagram_server.replies.application.port.in;

import com.example.new_instagram_server.replies.adapter.in.dto.RepliesCreateRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesDeleteRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesEditRequestDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesCreateResponseDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesEditResponseDto;

public interface RepliesUseCase {
    RepliesCreateResponseDto createReplies(RepliesCreateRequestDto repliesCreateRequestDto);
    RepliesEditResponseDto editReplies(RepliesEditRequestDto repliesEditRequestDto);
    void deleteReplies(RepliesDeleteRequestDto repliesDeleteRequestDto);
}

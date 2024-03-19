package com.example.new_instagram_server.replies.adapter.out.dto;

import com.example.new_instagram_server.replies.domain.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRepliesResponseDto {
    private long id;
    private String content;
    private String nickname;
    private String profileImageUrl;

    public FeedRepliesResponseDto toDo(Replies replies) {
        return new FeedRepliesResponseDto(
                replies.getId(),
                replies.getContent(),
                replies.getUser().getNickname(),
                replies.getUser().getProfileImageUrl());
    }
}

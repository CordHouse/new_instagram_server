package com.example.new_instagram_server.replies.adapter.out.dto;

import com.example.new_instagram_server.replies.domain.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesCreateResponseDto {
    private long id;
    private String content;

    public RepliesCreateResponseDto toDo(Replies replies) {
        return new RepliesCreateResponseDto(replies.getId(), replies.getContent());
    }
}

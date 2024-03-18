package com.example.new_instagram_server.replies.adapter.out.dto;

import com.example.new_instagram_server.replies.domain.Replies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepliesEditResponseDto {
    private long id;
    private String content;

    public RepliesEditResponseDto toDo(Replies replies) {
        return new RepliesEditResponseDto(replies.getId(), replies.getContent());
    }
}

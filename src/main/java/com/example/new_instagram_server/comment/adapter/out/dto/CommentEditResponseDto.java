package com.example.new_instagram_server.comment.adapter.out.dto;

import com.example.new_instagram_server.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditResponseDto {
    private long commentId;
    private String content;

    public CommentEditResponseDto toDo(Comment comment) {
        return new CommentEditResponseDto(comment.getId(), comment.getContent());
    }
}

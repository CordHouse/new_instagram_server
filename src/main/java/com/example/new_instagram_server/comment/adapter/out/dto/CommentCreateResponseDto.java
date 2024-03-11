package com.example.new_instagram_server.comment.adapter.out.dto;

import com.example.new_instagram_server.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateResponseDto {
    private long commentId;
    private String content;

    public CommentCreateResponseDto toDo(Comment comment) {
        return new CommentCreateResponseDto(comment.getId(), comment.getContent());
    }
}

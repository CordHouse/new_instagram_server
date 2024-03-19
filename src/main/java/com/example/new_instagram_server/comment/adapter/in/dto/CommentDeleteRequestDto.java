package com.example.new_instagram_server.comment.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequestDto {
    @NotNull(message = "삭제할 댓글을 선택해주세요.")
    private long commentId;
}

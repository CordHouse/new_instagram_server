package com.example.new_instagram_server.comment.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {
    @NotNull(message = "댓글을 작성할 게시글의 번호를 입력해주세요.")
    private long postId;
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;
}

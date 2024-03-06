package com.example.new_instagram_server.posts.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsDeleteRequestDto {
    @NotNull(message = "삭제할 게시글을 선택해주세요.")
    private long id;
}

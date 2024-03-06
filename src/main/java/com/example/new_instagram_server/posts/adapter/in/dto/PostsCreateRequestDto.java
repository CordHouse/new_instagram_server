package com.example.new_instagram_server.posts.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsCreateRequestDto {
    @NotBlank(message = "게시물 내용을 입력해주세요.")
    private String content;

    private MultipartFile image;
}

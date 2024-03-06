package com.example.new_instagram_server.posts.adapter.out.dto;

import com.example.new_instagram_server.posts.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsCreateResponseDto {
    private long id;
    private String image;
    private String content;

    public PostsCreateResponseDto toDo(Posts posts) {
        return new PostsCreateResponseDto(posts.getId(), posts.getImage(), posts.getContent());
    }
}

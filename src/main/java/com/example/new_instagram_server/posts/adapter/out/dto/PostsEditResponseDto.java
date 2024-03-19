package com.example.new_instagram_server.posts.adapter.out.dto;

import com.example.new_instagram_server.posts.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsEditResponseDto {
    private long id;
    private String imageUrl;
    private String content;

    public PostsEditResponseDto toDo(Posts posts) {
        return new PostsEditResponseDto(posts.getId(), posts.getImage(), posts.getContent());
    }
}

package com.example.new_instagram_server.posts.adapter.out.dto;

import com.example.new_instagram_server.comment.adapter.out.FeedCommentResponseDto;
import com.example.new_instagram_server.posts.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostsResponseDto {
    private long id;
    private String content;
    private String imageUrl;
    private List<FeedCommentResponseDto> comments;

    public FeedPostsResponseDto toDo(Posts posts) {
        return new FeedPostsResponseDto(
                posts.getId(),
                posts.getContent(),
                posts.getImage(),
                posts.getComments().stream().map(comment -> new FeedCommentResponseDto().toDo(comment)).collect(Collectors.toList())
        );
    }
}

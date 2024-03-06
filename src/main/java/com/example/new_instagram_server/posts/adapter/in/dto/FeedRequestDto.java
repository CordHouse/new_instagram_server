package com.example.new_instagram_server.posts.adapter.in.dto;

import com.example.new_instagram_server.posts.adapter.out.dto.FeedResponseDto;
import com.example.new_instagram_server.posts.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {
    private List<FeedPostsResponseDto> posts;

    public FeedResponseDto toDo(Page<Posts> posts) {
        return new FeedResponseDto(
                posts.stream().map(post -> new FeedPostsResponseDto().toDo(post)).collect(Collectors.toList())
        );
    }
}

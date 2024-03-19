package com.example.new_instagram_server.posts.application.port.in;

import com.example.new_instagram_server.posts.adapter.in.dto.FeedRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsCreateRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsDeleteRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsEditRequestDto;
import com.example.new_instagram_server.posts.adapter.out.dto.FeedResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsCreateResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsEditResponseDto;
import org.springframework.data.domain.Pageable;

public interface PostsUseCase {
    // 게시글 생성
    PostsCreateResponseDto createPosts(PostsCreateRequestDto postsCreateRequestDto);

    // 게시글 조회 (피드조회)
    FeedResponseDto getFeed(Pageable pageable, FeedRequestDto feedRequestDto);

    // 게시글 수정
    PostsEditResponseDto editPosts(PostsEditRequestDto postsEditRequestDto);

    // 게시글 삭제
    void deletePosts(PostsDeleteRequestDto postsDeleteRequestDto);
}

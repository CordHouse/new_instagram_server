package com.example.new_instagram_server.posts.application.port.out;

import com.example.new_instagram_server.posts.adapter.in.dto.FeedRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsDeleteRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsEditRequestDto;
import com.example.new_instagram_server.posts.domain.Posts;
import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostsRepository {
    void save(Posts posts);

    // 커서방식의 파싱(피드 조회)
    Optional<Page<Posts>> findByQUserPostsCustomCursorPaging(Pageable pageable, FeedRequestDto feedRequestDto);

    Optional<Posts> findByIdAndUser(PostsEditRequestDto postsEditRequestDto, User user);

    void deleteByIdAndUser(PostsDeleteRequestDto postsDeleteRequestDto, User user);
}

package com.example.new_instagram_server.posts.adapter.in;

import com.example.new_instagram_server.posts.adapter.in.dto.FeedRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsCreateRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsDeleteRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsEditRequestDto;
import com.example.new_instagram_server.posts.adapter.out.dto.FeedResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsCreateResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsEditResponseDto;
import com.example.new_instagram_server.posts.application.port.in.PostsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/posts")
public class PostsController {
    private final PostsUseCase postsUseCase;
    private final static int PAGE_SIZE = 2; // 피드 갯수는 2개씩 호출 (커서방식)

    // 게시글 생성
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public PostsCreateResponseDto createPost(@ModelAttribute @Valid PostsCreateRequestDto postsCreateRequestDto) {
        return postsUseCase.createPosts(postsCreateRequestDto);
    }

    // 게시글 조회 (피드 조회)
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public FeedResponseDto getFeed(
            @RequestBody @Valid FeedRequestDto feedRequestDto,
            @PageableDefault(size = PAGE_SIZE, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return postsUseCase.getFeed(pageable, feedRequestDto);
    }

    // 게시글 수정
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public PostsEditResponseDto editPosts(@ModelAttribute @Valid PostsEditRequestDto postsEditRequestDto) {
        return postsUseCase.editPosts(postsEditRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deletePosts(@RequestBody @Valid PostsDeleteRequestDto postDeleteRequestDto) {
        postsUseCase.deletePosts(postDeleteRequestDto);
    }
}

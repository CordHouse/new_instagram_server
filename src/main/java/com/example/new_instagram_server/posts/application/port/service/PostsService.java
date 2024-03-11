package com.example.new_instagram_server.posts.application.port.service;

import com.example.new_instagram_server.posts.adapter.in.dto.FeedRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsCreateRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsDeleteRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsEditRequestDto;
import com.example.new_instagram_server.posts.adapter.out.dto.FeedResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsCreateResponseDto;
import com.example.new_instagram_server.posts.adapter.out.dto.PostsEditResponseDto;
import com.example.new_instagram_server.posts.advice.exception.NotFoundFeedException;
import com.example.new_instagram_server.posts.application.port.out.PostsRepository;
import com.example.new_instagram_server.posts.application.port.in.PostsUseCase;
import com.example.new_instagram_server.posts.domain.Posts;
import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostsService implements PostsUseCase {
    private final UserRepository userRepository;
    private final GetAuthentication getAuthentication;
    private final PostsRepository postsRepository;
    // 게시글 생성
    @Override
    public PostsCreateResponseDto createPosts(PostsCreateRequestDto postsCreateRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);

        Posts newPosts = new Posts(
                user,
                postsCreateRequestDto.getImage().getOriginalFilename(),
                postsCreateRequestDto.getContent()
        );

        postsRepository.save(newPosts);
        return new PostsCreateResponseDto().toDo(newPosts);
    }

    // 게시글 조회 (피드 조회)
    @Override
    @Transactional(readOnly = true)
    public FeedResponseDto getFeed(Pageable pageable, FeedRequestDto feedRequestDto) {
        Page<Posts> feedList = postsRepository.findByQUserPostsCustomCursorPaging(pageable, feedRequestDto)
                .orElseThrow(NotFoundFeedException::new);

        return new FeedResponseDto().toDo(feedList);
    }

    // 게시글 수정
    @Override
    public PostsEditResponseDto editPosts(PostsEditRequestDto postsEditRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);

        Posts editPosts = postsRepository.findByIdAndUser(postsEditRequestDto, user)
                .orElseThrow(NotFoundFeedException::new);

        if(postsEditRequestDto.getImage() != null) {
            editPosts.setImage(postsEditRequestDto.getImage().getOriginalFilename());
        }
        editPosts.setContent(postsEditRequestDto.getContent());

        return new PostsEditResponseDto().toDo(editPosts);
    }

    // 게시글 삭제
    @Override
    public void deletePosts(PostsDeleteRequestDto postsDeleteRequestDto) {
        User user = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        postsRepository.deleteByIdAndUser(postsDeleteRequestDto, user);
    }
}

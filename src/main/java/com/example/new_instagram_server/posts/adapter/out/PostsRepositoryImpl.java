package com.example.new_instagram_server.posts.adapter.out;

import com.example.new_instagram_server.posts.adapter.in.dto.FeedRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsDeleteRequestDto;
import com.example.new_instagram_server.posts.adapter.in.dto.PostsEditRequestDto;
import com.example.new_instagram_server.posts.application.port.out.PostsJpaRepository;
import com.example.new_instagram_server.posts.application.port.out.PostsRepository;
import com.example.new_instagram_server.posts.domain.Posts;
import com.example.new_instagram_server.posts.domain.QPosts;
import com.example.new_instagram_server.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostsRepositoryImpl implements PostsRepository {
    private final PostsJpaRepository postsJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Posts posts) {
        postsJpaRepository.save(posts);
    }

    // 커서방식의 파싱(피드 조회)
    @Override
    public Optional<Page<Posts>> findByQUserPostsCustomCursorPaging(Pageable pageable, FeedRequestDto feedRequestDto) {
        QPosts qPosts = QPosts.posts;

        List<Posts> posts = jpaQueryFactory
                .select(qPosts)
                .from(qPosts)
                .leftJoin(qPosts.user)
                .leftJoin(qPosts.comments)
                .fetchJoin()
                .where(qPosts.user.id.eq(feedRequestDto.getUserId())
                        .and(cursorId(feedRequestDto.getCursor(), qPosts)))
                .limit(pageable.getPageSize())
                .fetch();
        return Optional.of(new PageImpl<>(posts));
    }

    @Override
    public Optional<Posts> findByIdAndUser(PostsEditRequestDto postsEditRequestDto, User user) {
        return postsJpaRepository.findByIdAndUser(postsEditRequestDto.getId(), user);
    }

    @Override
    public void deleteByIdAndUser(PostsDeleteRequestDto postsDeleteRequestDto, User user) {
        postsJpaRepository.deleteByIdAndUser(postsDeleteRequestDto.getId(), user);
    }

    @Override
    public Optional<Posts> findById(long id) {
        return postsJpaRepository.findById(id);
    }

    private BooleanExpression cursorId(Long cursorId, QPosts qPosts) {
        if(cursorId == null) {
            return null;
        }
        // post.id.gt -> where posts0_.id > ? 으로 출력 그 다음 자료들이 나와야 하는 경우 ( 게시글에 적합 )
        // post.id.lt -> where posts0_.id < ? 으로 출력 그 이전 자료들이 나와야 하는 경우 ( 채팅방에 적합 )
        return qPosts.id.gt(cursorId);
    }
}

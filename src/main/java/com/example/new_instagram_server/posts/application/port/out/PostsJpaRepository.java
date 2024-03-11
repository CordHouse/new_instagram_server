package com.example.new_instagram_server.posts.application.port.out;

import com.example.new_instagram_server.posts.domain.Posts;
import com.example.new_instagram_server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsJpaRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByIdAndUser(Long id, User user);

    void deleteByIdAndUser(Long id, User user);
}

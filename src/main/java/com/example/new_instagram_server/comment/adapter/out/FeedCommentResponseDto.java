package com.example.new_instagram_server.comment.adapter.out;

import com.example.new_instagram_server.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedCommentResponseDto {
    private long id;
    private String content;
    private String nickname;
    private String profileImageUrl;
    private List<FeedRepliesResponseDto> replies;

    public FeedCommentResponseDto toDo(Comment comment) {
        return new FeedCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getNickname(),
                comment.getUser().getProfileImageUrl(),
                comment.getReplies().stream().map(replies -> new FeedRepliesResponseDto().toDo(replies)).collect(Collectors.toList())
        );
    }
}

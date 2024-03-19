package com.example.new_instagram_server.posts.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedRequestDto {
    @NotNull(message = "피드 조회할 유저 id 값을 입력해주세요.")
    private long userId;
    @Nullable
    private long cursor;
}

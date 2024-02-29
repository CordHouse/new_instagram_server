package com.example.new_instagram_server.follow.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUnFollowRequestDto {
    @NotNull(message = "팔로우 취소할 사람을 선택해주세요.")
    private long unFollowUserId;
}

package com.example.new_instagram_server.follow.adapter.in;

import com.example.new_instagram_server.follow.adapter.in.dto.UserFollowRequestDto;
import com.example.new_instagram_server.follow.adapter.in.dto.UserUnFollowRequestDto;
import com.example.new_instagram_server.follow.application.port.in.FollowUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class FollowController {
    private final FollowUseCase followUseCase;

    // 팔로우
    @PostMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void userFollow(@RequestBody @Valid UserFollowRequestDto userFollowRequestDto) {
        followUseCase.userFollow(userFollowRequestDto);
    }

    // 팔로우 취소
    @DeleteMapping("/follow")
    @ResponseStatus(HttpStatus.OK)
    public void userUnFollow(@RequestBody @Valid UserUnFollowRequestDto userUnFollowRequestDto) {
        followUseCase.userUnFollow(userUnFollowRequestDto);
    }
}

package com.example.new_instagram_server.replies.adapter.in;

import com.example.new_instagram_server.replies.adapter.in.dto.RepliesCreateRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesDeleteRequestDto;
import com.example.new_instagram_server.replies.adapter.in.dto.RepliesEditRequestDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesCreateResponseDto;
import com.example.new_instagram_server.replies.adapter.out.dto.RepliesEditResponseDto;
import com.example.new_instagram_server.replies.application.port.in.RepliesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/posts/replies")
public class RepliesController {
    private final RepliesUseCase repliesUseCase;

    // 답글 생성
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public RepliesCreateResponseDto createReplies(
            @RequestBody @Valid RepliesCreateRequestDto repliesCreateRequestDto) {
        return repliesUseCase.createReplies(repliesCreateRequestDto);
    }

    // 답글 수정
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public RepliesEditResponseDto editReplies(
            @RequestBody @Valid RepliesEditRequestDto repliesEditRequestDto) {
        return repliesUseCase.editReplies(repliesEditRequestDto);
    }

    // 답글 삭제
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void deleteReplies(@RequestBody @Valid RepliesDeleteRequestDto repliesDeleteRequestDto) {
        repliesUseCase.deleteReplies(repliesDeleteRequestDto);
    }
}

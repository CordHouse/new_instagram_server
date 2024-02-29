package com.example.new_instagram_server.follow.application.port.service;

import com.example.new_instagram_server.follow.adapter.in.dto.UserFollowRequestDto;
import com.example.new_instagram_server.follow.adapter.in.dto.UserUnFollowRequestDto;
import com.example.new_instagram_server.follow.advice.exception.AlreadyFollowException;
import com.example.new_instagram_server.follow.application.port.in.FollowUseCase;
import com.example.new_instagram_server.follow.application.port.out.FollowRepository;
import com.example.new_instagram_server.follow.domain.Follow;
import com.example.new_instagram_server.user.advice.exception.LoginTimeOutException;
import com.example.new_instagram_server.user.advice.exception.NotFoundUserException;
import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService implements FollowUseCase {
    private static final int FOLLOW_COUNT = 1;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final GetAuthentication getAuthentication;
    @Override
    @Transactional
    public void userFollow(UserFollowRequestDto userFollowRequestDto) {
        User followSenderUser = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        User followReceiverUser = userRepository
                .findById(userFollowRequestDto.getReceiveUserId())
                .orElseThrow(NotFoundUserException::new);
        followConfirm(followSenderUser, followReceiverUser);
        followSenderUser.setFollow(followSenderUser.getFollow()+FOLLOW_COUNT);
        followReceiverUser.setFollowing(followReceiverUser.getFollowing()+FOLLOW_COUNT);
    }

    @Override
    @Transactional
    public void userUnFollow(UserUnFollowRequestDto userUnFollowRequestDto) {
        User followSenderUser = userRepository.findByNickname(getAuthentication.getAuthentication().getName())
                .orElseThrow(LoginTimeOutException::new);
        User unFollowReceiverUser = userRepository
                .findById(userUnFollowRequestDto.getUnFollowUserId())
                .orElseThrow(NotFoundUserException::new);
        followSenderUser.setFollow(followSenderUser.getFollow()-FOLLOW_COUNT);
        unFollowReceiverUser.setFollowing(unFollowReceiverUser.getFollowing()-FOLLOW_COUNT);
        followRepository.deleteByReceiver(unFollowReceiverUser);
    }

    /***
     * 팔로우가 가능한지 확인한다. ( 이미 신청한 사용자라면 예외처리 )
     * @param user 팔로우 신청자
     * @param followResponseUser 팔로우 신청을 받는 사람
     */
    @Transactional
    public void followConfirm(User followSenderUser, User followResponseUser) {
        Follow newFollow = followRepository.findBySenderAndReceiver(followSenderUser, followResponseUser);
        if(newFollow == null) {
            newFollow = new Follow(followSenderUser, followResponseUser);
            followRepository.save(newFollow);
            return;
        }
        throw new AlreadyFollowException();
    }
}

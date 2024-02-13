package com.example.new_instagram_server.config.security;

import com.example.new_instagram_server.user.advice.exception.NotFoundUserException;
import com.example.new_instagram_server.user.application.port.out.UserRepository;
import com.example.new_instagram_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByNickname(username)
                .map(this::createUserDetails)
                .orElseThrow(NotFoundUserException::new);
    }

    public UserDetails createUserDetails(User user) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        // 여기 유저 패스워드 토큰으로 교체해보자
        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                Collections.singleton(simpleGrantedAuthority));
    }
}
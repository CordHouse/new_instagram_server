package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.config.security.jwt.TokenProvider;
import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import com.example.new_instagram_server.user.application.port.out.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenInfoImpl implements TokenInfo {
    private final TokenProvider tokenProvider;
    @Override
    public TokenResponseDto createToken(Authentication authentication, long id) {
        return tokenProvider.createToken(authentication, id);
    }
}

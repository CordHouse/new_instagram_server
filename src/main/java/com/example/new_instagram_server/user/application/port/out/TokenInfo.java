package com.example.new_instagram_server.user.application.port.out;

import com.example.new_instagram_server.user.adapter.out.dto.TokenResponseDto;
import org.springframework.security.core.Authentication;

public interface TokenInfo {
    TokenResponseDto createToken(Authentication authentication, long id);
}

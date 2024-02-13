package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.application.port.out.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

@RequiredArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Override
    public Authentication getAuthentication(UsernamePasswordAuthenticationToken authToken) {
        return authenticationManagerBuilder.getObject().authenticate(authToken);
    }
}

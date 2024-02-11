package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.application.port.out.GetAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetAuthenticationImpl implements GetAuthentication {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

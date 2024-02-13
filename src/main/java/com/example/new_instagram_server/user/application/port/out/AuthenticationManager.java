package com.example.new_instagram_server.user.application.port.out;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public interface AuthenticationManager {
    Authentication getAuthentication(UsernamePasswordAuthenticationToken authToken);
}

package com.example.new_instagram_server.user.application.port.out;

import org.springframework.security.core.Authentication;

public interface GetAuthentication {
    Authentication getAuthentication();
}

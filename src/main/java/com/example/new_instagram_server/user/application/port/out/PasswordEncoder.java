package com.example.new_instagram_server.user.application.port.out;

public interface PasswordEncoder {
    String encoder(String password);
    boolean matches(String inputPassword, String databasePassword);
}

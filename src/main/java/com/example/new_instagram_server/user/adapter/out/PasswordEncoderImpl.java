package com.example.new_instagram_server.user.adapter.out;

import com.example.new_instagram_server.user.application.port.out.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 입력 받은 비밀번호 암호화
    @Override
    public String encoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    // 비밀번호를 비교하여 일치여부 확인
    @Override
    public boolean matches(String inputPassword, String databasePassword) {
        return bCryptPasswordEncoder.matches(inputPassword, databasePassword);
    }
}

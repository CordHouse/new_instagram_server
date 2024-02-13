package com.example.new_instagram_server.config.security;

import com.example.new_instagram_server.config.security.handler.AuthenticationEntryPointHandler;
import com.example.new_instagram_server.config.security.handler.JwtAccessDeniedHandler;
import com.example.new_instagram_server.config.security.jwt.JwtSecurityConfig;
import com.example.new_instagram_server.config.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;

    private static final String[] WHITE_LIST = {
            "/user/sign-up",
            "/user/sign-in",
            "/user/profile"
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPointHandler)

                .and()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, WHITE_LIST).permitAll()
                .antMatchers("/**").access("hasRole(\"ROLE_MEMBER\")")
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
        return httpSecurity.build();
    }
}

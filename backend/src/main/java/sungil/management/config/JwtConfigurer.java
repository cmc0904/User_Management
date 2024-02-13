package sungil.management.config;

import lombok.Data;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sungil.management.jwt.JwtTokenProvider;
import sungil.management.jwt.JwtTokenValidator;

public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenValidator jwtTokenValidator;

    public JwtConfigurer(JwtTokenValidator jwtTokenValidator) {
        this.jwtTokenValidator = jwtTokenValidator;
    }

    @Override
    public void configure(HttpSecurity http) {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenValidator);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
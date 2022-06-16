package com.skinnovation.citizen.common.config;


import com.skinnovation.citizen.util.encryption.PasswordHashing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordHashing();
    }
}

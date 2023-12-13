package com.example.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private String[] permitEndpoints = new String[]{"/swagger-ui/**", "/v3/api-docs/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(withDefaults()).authorizeHttpRequests((requests) -> requests
            // allow anonymous access to the root page
            .requestMatchers(permitEndpoints).permitAll()
            .anyRequest().authenticated());
        // enable OAuth2/OIDC
        http.oauth2ResourceServer(
            (oauth2ResourceServer) -> oauth2ResourceServer.jwt(withDefaults()));
        return http.build();
    }
}

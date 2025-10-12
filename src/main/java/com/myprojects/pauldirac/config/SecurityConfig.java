package com.myprojects.pauldirac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http
                // For stateless REST APIs, disable CSRF. If you need CSRF, see section 4.
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Read endpoints
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/cars/**").authenticated()
                        // Allow preflight (see section 3)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Writes require auth (or switch to permitAll while testing)
                        .requestMatchers(HttpMethod.POST, "/api/employees/**").authenticated()
                        //.requestMatchers(HttpMethod.GET,"/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        //.anyRequest().permitAll()
                )
                // Pick one auth mechanism you actually use:
                .httpBasic(Customizer.withDefaults()) // dev/basic
                // .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults())) // if JWT
                .build();
    }

}

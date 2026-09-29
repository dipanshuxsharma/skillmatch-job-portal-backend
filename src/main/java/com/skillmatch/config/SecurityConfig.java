package com.skillmatch.config;

import com.skillmatch.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ==============================
    // CORS CONFIGURATION
    // ==============================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://127.0.0.1:5500",
                        "http://localhost:5500"
                )
        );

        configuration.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                Arrays.asList("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                // IMPORTANT: Enable CORS
                .cors(cors ->
                        cors.configurationSource(
                                corsConfigurationSource()
                        )
                )

                .formLogin(form -> form.disable())

                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth

                        // ==============================
                        // PUBLIC APIs + SWAGGER
                        // ==============================
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/test",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // ==============================
                        // JOB APIs
                        // ==============================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/jobs"
                        )
                        .hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/jobs/**"
                        )
                        .hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/jobs/**"
                        )
                        .hasRole("RECRUITER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/jobs/**"
                        )
                        .hasAnyRole(
                                "JOB_SEEKER",
                                "RECRUITER"
                        )

                        // ==============================
                        // RESUME APIs
                        // ==============================
                        .requestMatchers(
                                "/api/resumes/**"
                        )
                        .hasRole("JOB_SEEKER")

                        // ==============================
                        // MATCHING APIs
                        // ==============================
                        .requestMatchers(
                                "/api/match/**"
                        )
                        .hasRole("JOB_SEEKER")

                        // ==============================
                        // RECOMMENDATION APIs
                        // ==============================
                        .requestMatchers(
                                "/api/recommendations/**"
                        )
                        .hasRole("JOB_SEEKER")

                        // ==============================
                        // APPLICATION APIs
                        // ==============================
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/jobs",
                                "/api/jobs/**"
                        )
                        .hasAnyRole(
                                "JOB_SEEKER",
                                "RECRUITER"
                        )

                        // ==============================
                        // EVERYTHING ELSE
                        // ==============================
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
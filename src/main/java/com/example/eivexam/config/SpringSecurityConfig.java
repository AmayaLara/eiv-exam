package com.example.eivexam.config;

import com.example.eivexam.utils.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req ->
            req
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers("/personas/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/usuarios/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/localidades/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/localidades/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/provincias/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/provincias/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/documentos/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/documentos/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
        )
        .sessionManagement(sessionManager ->
            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}

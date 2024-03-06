package com.example.eivexam.controller;

import com.example.eivexam.service.AuthService;
import com.example.eivexam.utils.dto.request.RegisterRequestDto;
import com.example.eivexam.utils.dto.response.LoginRequest;
import com.example.eivexam.utils.dto.response.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public TokenResponse register(@RequestBody RegisterRequestDto usuario)
      throws JsonProcessingException, AuthenticationException, BadRequestException {
    return authService.register(usuario);
  }

  @PostMapping(value = "login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException, JsonProcessingException {
    return ResponseEntity.ok(authService.login(request));
  }
}

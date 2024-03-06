package com.example.eivexam.service;

import com.example.eivexam.utils.dto.request.RegisterRequestDto;
import com.example.eivexam.utils.dto.response.LoginRequest;
import com.example.eivexam.utils.dto.response.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.BadRequestException;

import javax.naming.AuthenticationException;

public interface AuthService {
  TokenResponse register(RegisterRequestDto usuario)
      throws JsonProcessingException, AuthenticationException, BadRequestException;

  TokenResponse login(LoginRequest usuario) throws JsonProcessingException, AuthenticationException;
}

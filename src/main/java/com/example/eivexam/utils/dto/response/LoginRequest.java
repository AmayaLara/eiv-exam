package com.example.eivexam.utils.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
  private String username;
  private String password;
}

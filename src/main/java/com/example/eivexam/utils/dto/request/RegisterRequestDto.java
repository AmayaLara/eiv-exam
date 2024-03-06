package com.example.eivexam.utils.dto.request;

import com.example.eivexam.utils.enums.Rol;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto extends PersonaRequestDto {
  private String username;
  private String password;
  private Rol rol;
}

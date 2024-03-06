package com.example.eivexam.utils.dto;

import com.example.eivexam.utils.dto.response.PersonaResponseDto;
import com.example.eivexam.utils.enums.Rol;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto extends PersonaResponseDto {
  private String username;
  private Rol rol;
}

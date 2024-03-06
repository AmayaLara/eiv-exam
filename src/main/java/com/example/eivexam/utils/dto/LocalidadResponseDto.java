package com.example.eivexam.utils.dto;

import com.example.eivexam.utils.dto.response.ProvinciaDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalidadResponseDto {
  private String nombre;
  private String codigoPostal;
  private ProvinciaDto provincia;
}

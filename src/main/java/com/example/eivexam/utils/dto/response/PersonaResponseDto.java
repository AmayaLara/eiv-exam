package com.example.eivexam.utils.dto.response;

import com.example.eivexam.utils.dto.LocalidadResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonaResponseDto {

  private ClavePersonaDto id;
  private String nombreCompleto;
  private String email;
  private LocalDate fechaNacimiento;
  private String genero;
  private int esArgentino;
  private String codigoPostal;
  private LocalidadResponseDto localidad;
}

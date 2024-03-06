package com.example.eivexam.utils.dto.request;

import com.example.eivexam.utils.enums.Genero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PersonaRequestDto {

  private String nombrecompleto;
  private Integer numeroDocumento;
  private String email;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaNacimiento;
  private Genero genero;
  private int esArgentino;
  private String codigoPostal;
  private String tipoDocumentoAbreviatura;
  private String nombreLocalidad;
}

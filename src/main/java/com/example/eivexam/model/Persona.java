package com.example.eivexam.model;

import com.example.eivexam.utils.ClaveCompuestaPersona;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

  @EmbeddedId
  private ClaveCompuestaPersona id;
  @Column(unique = true)
  private String nombreCompleto;
  @Column(unique = true)
  private String email;
  private LocalDate fechaNacimiento;
  private String genero;
  private int esArgentino;
  private String codigoPostal;

  @ManyToOne
  private Localidad localidad;
}

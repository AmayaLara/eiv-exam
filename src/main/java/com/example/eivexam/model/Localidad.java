package com.example.eivexam.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "localidades")
public class Localidad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String nombre;
  @Column(unique = true)
  private String codigoPostal;

  @ManyToOne
  private Provincia provincia;
}

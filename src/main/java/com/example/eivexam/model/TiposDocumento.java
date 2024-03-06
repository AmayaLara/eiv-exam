package com.example.eivexam.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tipos_documentos")
@NoArgsConstructor
@AllArgsConstructor
public class TiposDocumento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String nombre;
  @Column(unique = true)
  private String abreviatura;
}

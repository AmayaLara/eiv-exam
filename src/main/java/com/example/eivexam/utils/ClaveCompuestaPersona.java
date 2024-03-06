package com.example.eivexam.utils;

import com.example.eivexam.model.TiposDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ClaveCompuestaPersona {

  @Column(unique = true, name = "numero_documento")
  private Integer numeroDocumento;

  @ManyToOne
  @JoinColumn(name = "id_tipo_documento")
  private TiposDocumento tiposDocumento;
}
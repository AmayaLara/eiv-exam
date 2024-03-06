package com.example.eivexam.utils.dto.response;

import com.example.eivexam.utils.dto.TiposDocumentoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClavePersonaDto {

  @JsonProperty("numero_documento")
  private Integer numeroDocumento;
  @JsonProperty("id_tipo_documento")
  private TiposDocumentoDto tiposDocumento;
}

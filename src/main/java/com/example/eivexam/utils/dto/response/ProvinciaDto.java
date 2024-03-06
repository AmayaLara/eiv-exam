package com.example.eivexam.utils.dto.response;

import com.example.eivexam.utils.enums.Region;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvinciaDto {
  private String nombre;
  private Region region;
}

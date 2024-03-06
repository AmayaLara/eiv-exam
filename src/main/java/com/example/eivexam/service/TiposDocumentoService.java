package com.example.eivexam.service;

import com.example.eivexam.model.TiposDocumento;
import com.example.eivexam.utils.dto.TiposDocumentoDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TiposDocumentoService {
  TiposDocumento findTipoDocumentoByAbreviatura(String abreviatura) throws BadRequestException;

  List<TiposDocumentoDto> getAll();

  TiposDocumentoDto getById(Integer id) throws BadRequestException;

  TiposDocumentoDto save(TiposDocumentoDto request) throws BadRequestException;

  TiposDocumentoDto update(Integer id, TiposDocumentoDto request) throws BadRequestException;

  ResponseEntity<Void> delete(Integer id) throws BadRequestException;
}

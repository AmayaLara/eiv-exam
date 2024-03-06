package com.example.eivexam.service;

import com.example.eivexam.utils.dto.UsuarioDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {

  List<UsuarioDto> getAll();

  UsuarioDto getById(Integer id, String abreviatura) throws BadRequestException;

  UsuarioDto update(Integer numeroDocumento, String abreviatura, UsuarioDto request) throws BadRequestException;

  ResponseEntity<Void> delete(Integer numeroDocumento, String abreviatura) throws BadRequestException;
}

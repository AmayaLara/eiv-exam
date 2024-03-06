package com.example.eivexam.service;

import com.example.eivexam.model.Localidad;
import com.example.eivexam.utils.dto.LocalidadRequestDto;
import com.example.eivexam.utils.dto.LocalidadResponseDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocalidadService {

  List<LocalidadResponseDto> getAll();

  LocalidadResponseDto getById(Integer id) throws BadRequestException;

  LocalidadResponseDto save(LocalidadRequestDto request) throws BadRequestException;

  LocalidadResponseDto update(Integer id, LocalidadRequestDto request) throws BadRequestException;

  ResponseEntity<Void> delete(Integer id) throws BadRequestException;
  Localidad findLocalidadByNombre(String localidad) throws BadRequestException;
}

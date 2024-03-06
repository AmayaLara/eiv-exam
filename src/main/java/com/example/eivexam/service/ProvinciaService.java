package com.example.eivexam.service;


import com.example.eivexam.model.Provincia;
import com.example.eivexam.utils.dto.response.ProvinciaDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProvinciaService {

  List<ProvinciaDto> getAll();

  ProvinciaDto getById(Integer id) throws BadRequestException;

  ProvinciaDto save(ProvinciaDto request) throws BadRequestException;

  ProvinciaDto update(Integer id, ProvinciaDto request) throws BadRequestException;

  ResponseEntity<Void> delete(Integer id) throws BadRequestException;

  Provincia findProvinciaByName(String name) throws BadRequestException;
}

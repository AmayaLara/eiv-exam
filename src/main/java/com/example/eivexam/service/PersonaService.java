package com.example.eivexam.service;

import com.example.eivexam.utils.dto.request.PersonaRequestDto;
import com.example.eivexam.utils.dto.response.PersonaResponseDto;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonaService {
  List<PersonaResponseDto> getAll();
  PersonaResponseDto getById(Integer id, String abreviatura) throws BadRequestException;
  PersonaResponseDto save(PersonaRequestDto request) throws BadRequestException;
  PersonaResponseDto update(Integer numeroDocumento, PersonaRequestDto request) throws BadRequestException;
  ResponseEntity<Void> delete(Integer numeroDocumento, String abreviatura) throws BadRequestException;
}

package com.example.eivexam.controller;

import com.example.eivexam.service.LocalidadService;
import com.example.eivexam.utils.dto.LocalidadRequestDto;
import com.example.eivexam.utils.dto.LocalidadResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
@RequiredArgsConstructor
public class LocalidadController {

  private final LocalidadService localidadService;

  @GetMapping
  public ResponseEntity<List<LocalidadResponseDto>> getAll() {
    return ResponseEntity.ok(localidadService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<LocalidadResponseDto> getById(@PathVariable("id") Integer id) throws BadRequestException {
    return ResponseEntity.ok(localidadService.getById(id));
  }

  @PostMapping
  public ResponseEntity<LocalidadResponseDto> save(@RequestBody LocalidadRequestDto request) throws BadRequestException {
    return new ResponseEntity<LocalidadResponseDto>(localidadService.save(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<LocalidadResponseDto> update(@PathVariable Integer id,
                                                     @RequestBody LocalidadRequestDto request) throws BadRequestException {
    return ResponseEntity.ok(localidadService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws BadRequestException {
    return localidadService.delete(id);
  }
}

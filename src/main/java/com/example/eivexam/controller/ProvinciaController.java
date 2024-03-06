package com.example.eivexam.controller;

import com.example.eivexam.service.ProvinciaService;
import com.example.eivexam.utils.dto.response.ProvinciaDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@RequiredArgsConstructor
public class ProvinciaController {

  private final ProvinciaService provinciaService;


  @GetMapping
  public ResponseEntity<List<ProvinciaDto>> getAll() {
    return ResponseEntity.ok(provinciaService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProvinciaDto> getById(@PathVariable("id") Integer id) throws BadRequestException {
    return ResponseEntity.ok(provinciaService.getById(id));
  }

  @PostMapping
  public ResponseEntity<ProvinciaDto> save(@RequestBody ProvinciaDto request) throws BadRequestException {
    return new ResponseEntity<ProvinciaDto>(provinciaService.save(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProvinciaDto> update(@PathVariable Integer id,
                                             @RequestBody ProvinciaDto request) throws BadRequestException {
    return ResponseEntity.ok(provinciaService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws BadRequestException {
    return provinciaService.delete(id);
  }
}

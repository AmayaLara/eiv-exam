package com.example.eivexam.service.Impl;


import com.example.eivexam.model.Provincia;
import com.example.eivexam.repository.ProvinciaRepository;
import com.example.eivexam.service.ProvinciaService;
import com.example.eivexam.utils.dto.response.ProvinciaDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProvinciaServiceImpl implements ProvinciaService {

  private final ProvinciaRepository provinciaRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<ProvinciaDto> getAll() {
    return provinciaRepository.findAll()
        .stream().map(persona ->
            modelMapper.map(persona, ProvinciaDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public ProvinciaDto getById(Integer id) throws BadRequestException {
    return modelMapper.map(this.findProvinciaById(id), ProvinciaDto.class);
  }

  @Override
  public ProvinciaDto save(ProvinciaDto request) throws BadRequestException {
    Provincia provincia = Provincia.builder()
        .nombre(request.getNombre())
        .region(request.getRegion().name())
        .build();
    provinciaRepository.save(provincia);
    return modelMapper.map(provincia, ProvinciaDto.class);
  }

  @Override
  public ProvinciaDto update(Integer id, ProvinciaDto request) throws BadRequestException {
    Provincia provincia = this.findProvinciaById(id);
    provincia.setNombre(request.getNombre());
    provincia.setRegion(request.getRegion().name());
    return modelMapper.map(provincia, ProvinciaDto.class);
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) throws BadRequestException {
    if (provinciaRepository.existsById(id)) {
      Provincia provincia = this.findProvinciaById(id);
      provinciaRepository.delete(provincia);
      return ResponseEntity.noContent().build();
    }
    throw new BadRequestException("No existe provincia con ese id");
  }

  @Override
  public Provincia findProvinciaByName(String nombre) throws BadRequestException {
    return provinciaRepository.findByNombre(nombre)
        .orElseThrow(() -> new BadRequestException("No existe provincia con ese nombre"));
  }

  private Provincia findProvinciaById(Integer id) throws BadRequestException {
    return provinciaRepository.findById(id)
        .orElseThrow(
            () -> new BadRequestException("No existe provincia con ese id"));
  }
}

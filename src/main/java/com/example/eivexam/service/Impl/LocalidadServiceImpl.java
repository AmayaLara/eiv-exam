package com.example.eivexam.service.Impl;

import com.example.eivexam.model.Localidad;
import com.example.eivexam.model.Provincia;
import com.example.eivexam.repository.LocalidadRepository;
import com.example.eivexam.service.LocalidadService;
import com.example.eivexam.service.ProvinciaService;
import com.example.eivexam.utils.dto.LocalidadRequestDto;
import com.example.eivexam.utils.dto.LocalidadResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocalidadServiceImpl implements LocalidadService {

  private final LocalidadRepository localidadRepository;
  private final ProvinciaService provinciaService;
  private final ModelMapper modelMapper;

  @Override
  public List<LocalidadResponseDto> getAll() {
    return localidadRepository.findAll()
        .stream().map(localidad ->
            modelMapper.map(localidad, LocalidadResponseDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public LocalidadResponseDto getById(Integer id) throws BadRequestException {
    return modelMapper.map(this.findLocalidadById(id), LocalidadResponseDto.class);
  }

  @Override
  public LocalidadResponseDto save(LocalidadRequestDto request) throws BadRequestException {
    var e = localidadRepository.findByNombre(request.getNombre());
    if (e.isPresent()) {
      throw new BadRequestException("Localidad ya registrada en el sistema");
    }

    Provincia provincia = provinciaService.findProvinciaByName(request.getProvincia());

    Localidad localidad = Localidad.builder()
        .nombre(request.getNombre())
        .codigoPostal(request.getCodigoPostal())
        .provincia(provincia)
        .build();

    localidadRepository.save(localidad);

    return modelMapper.map(localidad, LocalidadResponseDto.class);
  }

  @Override
  public LocalidadResponseDto update(Integer id, LocalidadRequestDto request) throws BadRequestException {

    Localidad localidad = this.findLocalidadById(id);
    Provincia provincia = provinciaService.findProvinciaByName(request.getProvincia());

    localidad.setNombre(request.getNombre());
    localidad.setCodigoPostal(request.getCodigoPostal());
    localidad.setProvincia(provincia);

    return modelMapper.map(localidadRepository.save(localidad), LocalidadResponseDto.class);
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) throws BadRequestException {
    if (localidadRepository.existsById(id)) {
      Localidad localidad = this.findLocalidadById(id);
      localidadRepository.delete(localidad);
      return ResponseEntity.noContent().build();
    }
    throw new BadRequestException("No existe localidad con ese id");
  }

  @Override
  public Localidad findLocalidadByNombre(String localidad) throws BadRequestException {
    return localidadRepository.findByNombre(localidad)
        .orElseThrow(() -> new BadRequestException("Localidad no existe"));
  }

  private Localidad findLocalidadById(Integer id) throws BadRequestException {
    return localidadRepository.findById(id)
        .orElseThrow(
            () -> new BadRequestException("No existe localidad con ese id"));
  }
}

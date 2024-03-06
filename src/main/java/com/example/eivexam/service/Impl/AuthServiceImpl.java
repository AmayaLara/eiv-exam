package com.example.eivexam.service.Impl;

import com.example.eivexam.model.Localidad;
import com.example.eivexam.model.TiposDocumento;
import com.example.eivexam.model.Usuario;
import com.example.eivexam.repository.UsuarioRepository;
import com.example.eivexam.service.AuthService;
import com.example.eivexam.service.LocalidadService;
import com.example.eivexam.service.TiposDocumentoService;
import com.example.eivexam.utils.ClaveCompuestaPersona;
import com.example.eivexam.utils.dto.request.RegisterRequestDto;
import com.example.eivexam.utils.dto.response.LoginRequest;
import com.example.eivexam.utils.dto.response.TokenResponse;
import com.example.eivexam.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UsuarioRepository usuarioRepository;
  private final TiposDocumentoService tiposDocumentoService;
  private final LocalidadService localidadService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public TokenResponse register(RegisterRequestDto request)
      throws BadRequestException {

    TiposDocumento tiposDocumento = tiposDocumentoService.findTipoDocumentoByAbreviatura(
        request.getTipoDocumentoAbreviatura());

    ClaveCompuestaPersona id = new ClaveCompuestaPersona(request.getNumeroDocumento(), tiposDocumento);

    Localidad localidad = localidadService.findLocalidadByNombre(request.getNombreLocalidad());

    if (request.getCodigoPostal().isEmpty()) {
      request.setCodigoPostal(localidad.getCodigoPostal());
    }

    Usuario usuario = new Usuario(id,
        request.getNombrecompleto(),
        request.getEmail(),
        request.getFechaNacimiento(),
        request.getGenero().name(),
        request.getEsArgentino(),
        request.getCodigoPostal(),
        localidad,
        request.getUsername(),
        passwordEncoder.encode(request.getPassword()),
        request.getRol().name());

    usuarioRepository.save(usuario);

    return TokenResponse.builder()
        .accessToken(jwtService.getToken(usuario))
        .build();
  }

  @Override
  public TokenResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    UserDetails user = usuarioRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("Username o password incorrectos"));

    String token = jwtService.getToken(user);
    return TokenResponse.builder()
        .accessToken(token)
        .build();
  }
}

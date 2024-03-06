package com.example.eivexam.model;

import com.example.eivexam.utils.ClaveCompuestaPersona;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class Usuario extends Persona implements UserDetails {

  @Column(nullable = false)
  private String username;
  private String password;
  private String rol;

  public Usuario(ClaveCompuestaPersona id, String nombreCompleto, String email, LocalDate fechaNacimiento, String genero, int esArgentino, String codigoPostal, Localidad localidad, String username, String password, String rol) {
    super(id, nombreCompleto, email, fechaNacimiento, genero, esArgentino, codigoPostal, localidad);
    this.username = username;
    this.password = password;
    this.rol = rol;
  }

  public Usuario(String username, String password, String rol) {
    this.username = username;
    this.password = password;
    this.rol = rol;
  }

  public Usuario() {
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(rol));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

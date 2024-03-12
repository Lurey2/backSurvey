package com.formulario.encuesta.models.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.formulario.encuesta.models.dto.UserDto;

public interface UserService {
    UserDetailsService userDetailsService();

    UserDto findByEmail(String email);
}

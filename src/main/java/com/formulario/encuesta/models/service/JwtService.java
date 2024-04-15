package com.formulario.encuesta.models.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.formulario.encuesta.models.entities.User;

import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
    
    String extractUserName(String token) throws ExpiredJwtException;

    String generateToken(User userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}

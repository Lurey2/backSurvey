package com.formulario.encuesta.models.service;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;

public interface JwtService {
    
    String extractUserName(String token) throws ExpiredJwtException;

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}

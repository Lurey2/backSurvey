package com.formulario.encuesta.models.service;

import com.formulario.encuesta.models.dto.JwtAuthenticationResponse;
import com.formulario.encuesta.models.request.SignUpRequest;
import com.formulario.encuesta.models.request.SigninRequest;

public interface AuthenticationService  {
    
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

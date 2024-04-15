package com.formulario.encuesta.models.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formulario.encuesta.common.exception.ControllerException;
import com.formulario.encuesta.common.utils.builder.ErrorJsonBuilder;
import com.formulario.encuesta.common.utils.constanst.ControllerConstant;
import com.formulario.encuesta.models.dto.JwtAuthenticationResponse;
import com.formulario.encuesta.models.entities.Role;
import com.formulario.encuesta.models.entities.User;
import com.formulario.encuesta.models.repositories.UserRepository;
import com.formulario.encuesta.models.request.SignUpRequest;
import com.formulario.encuesta.models.request.SigninRequest;
import com.formulario.encuesta.models.service.AuthenticationService;
import com.formulario.encuesta.models.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        if (!userRepository.findByEmail(request.getEmail()).isEmpty()) {
            throw new ControllerException(HttpStatus.CONFLICT,
                    new ErrorJsonBuilder("email", "Ya existe una cuenta con ese correo"));
        }

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(   new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } catch (Exception e) {
            throw new ControllerException(HttpStatus.BAD_REQUEST,
            new ErrorJsonBuilder("Error", "Invalidemail or password"));
        }

    }
}

package com.formulario.encuesta.models.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formulario.encuesta.common.exception.ControllerException;
import com.formulario.encuesta.common.utils.builder.ErrorJsonBuilder;
import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.mapper.UserDtoMapper;
import com.formulario.encuesta.models.repositories.UserRepository;
import com.formulario.encuesta.models.service.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserDtoMapper mapper;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public UserDto findByEmail(String email) {
        var entity = this.userRepository.findByEmail(email);
        if (entity.isEmpty()){
            throw new ControllerException(HttpStatus.CONFLICT,
                    new ErrorJsonBuilder("email", "No existe una cuenta con ese correo"));
        }
        return mapper.toDomain(entity.get());
    }
}

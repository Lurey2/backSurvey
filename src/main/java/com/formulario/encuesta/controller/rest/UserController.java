package com.formulario.encuesta.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;


  
    @PostMapping( path =  "/findByEmail"  , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> findByEmail(@RequestBody String email){
        return  ResponseEntity.ok(service.findByEmail(email)) ;
    }
    
}
package com.formulario.encuesta.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.request.AnswerRequest;
import com.formulario.encuesta.models.request.SurveyRequest;
import com.formulario.encuesta.models.service.AnswerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService service;

    
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnswerDto>> getAll() {
        return new ResponseEntity<>( service.getAll() ,HttpStatus.OK) ;
    }

    @GetMapping( path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>( service.findById(id) ,HttpStatus.OK) ;
    }


    @PostMapping(path = "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerDto> create(@RequestBody AnswerRequest request){
        return new ResponseEntity<>(service.create(request) , HttpStatus.OK);
    }

    @PutMapping(path  = "/update/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerDto> update(@RequestBody AnswerRequest request , @PathVariable Long id){
        return new ResponseEntity<>(service.update(request , id) , HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}" )
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    
    
}

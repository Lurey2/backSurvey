package com.formulario.encuesta.models.service;

import java.util.List;

import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.request.AnswerRequest;

public interface AnswerService {
     
    AnswerDto findById(Long id);

    List<AnswerDto> getAll();

    AnswerDto create(AnswerRequest request);
    
    AnswerDto update(AnswerRequest request , Long id);

    void delete(Long id);
}

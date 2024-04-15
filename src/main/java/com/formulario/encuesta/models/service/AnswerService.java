package com.formulario.encuesta.models.service;

import java.util.List;

import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.entities.Answer;
import com.formulario.encuesta.models.request.AnswerRequest;

public interface AnswerService {
     
    AnswerDto findById(Long id);

    List<AnswerDto> getAll();
    List<AnswerDto> findByIdSurvey(Long idSurvey);

    AnswerDto create(AnswerRequest request);
    
    AnswerDto update(AnswerRequest request , Long id);

    void delete(Long id);
}

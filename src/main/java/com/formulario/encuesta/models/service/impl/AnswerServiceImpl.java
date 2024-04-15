package com.formulario.encuesta.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.formulario.encuesta.common.exception.ControllerException;
import com.formulario.encuesta.common.utils.builder.ErrorJsonBuilder;
import com.formulario.encuesta.common.utils.constanst.ControllerConstant;
import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.entities.Answer;
import com.formulario.encuesta.models.entities.Survey;
import com.formulario.encuesta.models.mapper.AnswerDtoMapper;
import com.formulario.encuesta.models.mapper.AnswerRequestMapper;
import com.formulario.encuesta.models.mapper.SurveyDtoMapper;
import com.formulario.encuesta.models.mapper.SurveyRequestMapper;
import com.formulario.encuesta.models.repositories.AnswerRepository;
import com.formulario.encuesta.models.repositories.SurveyRepository;
import com.formulario.encuesta.models.request.AnswerRequest;
import com.formulario.encuesta.models.service.AnswerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements  AnswerService {
    
    private final AnswerRepository repo;

    @Autowired
    private final  AnswerRequestMapper mapperRequest;

    
    @Autowired
    private final  AnswerDtoMapper mapperDto;


    @Override
    public AnswerDto findById(Long id) {
        return mapperDto.toDomain(repo.findById(id).get());
    }


    @Override
    public List<AnswerDto> getAll() {
        return repo.findAll().stream().map(mapperDto::toDomain).collect(Collectors.toList());
    }


    @Override
    public AnswerDto create(AnswerRequest request) {
        Answer entity  = mapperRequest.toDomain(request);
      
        repo.save(entity);
        return mapperDto.toDomain(entity);
    }


    @Override
    public AnswerDto update(AnswerRequest request, Long id) {
        Answer entity  = mapperRequest.toDomain(request);
        entity.setIdAnswer(id);
        repo.save(entity);
        return mapperDto.toDomain(entity);
    }


    @Override
    public void delete(Long id) {
        var answer = repo.findById(id);
        if ( answer.isEmpty()){
          throw new ControllerException(HttpStatus.NOT_FOUND, new ErrorJsonBuilder("error",  String.format(ControllerConstant.DATA_NOT_FOUND_MESSAGE_ERROR, id)));
        }
        repo.deleteById(id);
    }


    @Override
    public List<AnswerDto> findByIdSurvey(Long idSurvey) {
        return repo.findByIdSurvey(idSurvey).stream().map(mapperDto::toDomain).collect(Collectors.toList());
    }

}

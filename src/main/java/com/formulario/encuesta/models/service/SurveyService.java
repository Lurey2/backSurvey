package com.formulario.encuesta.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.SurveyPublicDto;
import com.formulario.encuesta.models.dto.SurveyUserDTO;
import com.formulario.encuesta.models.request.SurveyRequest;

public interface SurveyService {
    
    SurveyDto findById(Long id);

    List<SurveyDto> getAll();

    List<SurveyPublicDto> getAllActive();

    SurveyDto create(SurveyRequest request, Long idUser);
    
    SurveyDto update(SurveyRequest request , Long id);

    void delete(Long id);

    Page<SurveyUserDTO> findPageableByUserId(Long idUser , Pageable pageable );

    SurveyDto findByCode(String code);
}

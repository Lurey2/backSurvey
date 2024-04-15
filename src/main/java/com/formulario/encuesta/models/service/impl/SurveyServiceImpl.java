package com.formulario.encuesta.models.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.formulario.encuesta.common.exception.ControllerException;
import com.formulario.encuesta.common.utils.builder.ErrorJsonBuilder;
import com.formulario.encuesta.common.utils.constanst.ControllerConstant;
import com.formulario.encuesta.models.dto.SurveyAnswerDTO;
import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.SurveyPublicDto;
import com.formulario.encuesta.models.dto.SurveyUserDTO;
import com.formulario.encuesta.models.entities.Survey;
import com.formulario.encuesta.models.entities.User;
import com.formulario.encuesta.models.mapper.SurveyDtoMapper;
import com.formulario.encuesta.models.mapper.SurveyRequestMapper;
import com.formulario.encuesta.models.repositories.SurveyRepository;
import com.formulario.encuesta.models.request.SurveyRequest;
import com.formulario.encuesta.models.service.CryptoService;
import com.formulario.encuesta.models.service.SurveyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    
    private final SurveyRepository repo;

    @Autowired
    private final  SurveyRequestMapper mapperRequest;

    
    @Autowired
    private final  SurveyDtoMapper mapperDto;

    @Autowired
    private final CryptoService cryptoService;


    @Override
    public SurveyDto findById(Long id) {
        var entity = repo.findById(id);
        if (entity.isEmpty()){
            throw new ControllerException(HttpStatus.NOT_FOUND, new ErrorJsonBuilder("error",  String.format(ControllerConstant.DATA_NOT_FOUND_MESSAGE_ERROR, id)));
        }
        return mapperDto.toDomain(entity.get());
    }

    @Override
    public List<SurveyDto> getAll() {
        return repo.findAll().stream().map(mapperDto::toDomain).collect(Collectors.toList());
    }


    @Override
    public SurveyDto create(SurveyRequest request , Long idUser) {
        Survey entity  = mapperRequest.toDomain(request);
        User userEntity = User.builder().idUser(idUser).build();
        entity.setState(true);
        entity.setUser(userEntity);
        entity.setSharedCode(UUID.randomUUID().toString() );
        entity.setSharedEdit(UUID.randomUUID().toString() );
        repo.save(entity);
        return mapperDto.toDomain(entity);
    }

    @Override
    public SurveyDto update(SurveyRequest request, Long id) {

        Survey entity  = mapperRequest.toDomain(request);
        entity.setIdSurvey(id);
        entity.setState(true);
        
        repo.save(entity);
        return mapperDto.toDomain(entity);
    }

    @Override
    public void delete(Long id) {
        var survey = repo.findById(id);
        if ( survey.isEmpty()){
          throw new ControllerException(HttpStatus.NOT_FOUND, new ErrorJsonBuilder("error",  String.format(ControllerConstant.DATA_NOT_FOUND_MESSAGE_ERROR, id)));
       }
        repo.deleteById(id);
    }

    @Override
    public List<SurveyPublicDto> getAllActive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllActive'");
    }

    @Override
    public Page<SurveyUserDTO> findPageableByUserId(Long idUser, Pageable pageable) {
        return repo.findPageableByUserId(idUser, pageable);
    }

    @Override
    public SurveyDto findByCode(String code) {
        var entity = repo.findBySharedCode(code);
        if ( entity == null){
            return null;
        }
        return mapperDto.toDomain(entity);
    }

    @Override
    public Page<SurveyAnswerDTO> findSurveyAnswerByUser(Long idUser, String filterValue, Pageable pageable ) {

      
        if (filterValue.isEmpty() ){
            return repo.findSurveyAnswerByUser(idUser,pageable );
        }
        return repo.findSurveyAnswerByUserAndFilter(idUser, filterValue, pageable);
       
    }
  
    
}
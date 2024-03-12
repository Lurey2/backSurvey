package com.formulario.encuesta.controller.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.SurveyUserDTO;
import com.formulario.encuesta.models.entities.User;
import com.formulario.encuesta.models.request.SurveyRequest;
import com.formulario.encuesta.models.service.SurveyService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/survey")
@RequiredArgsConstructor
@EnableMethodSecurity
public class SurveyController {

    private final SurveyService service;

    
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SurveyDto>> getAll() {
        return new ResponseEntity<>( service.getAll() ,HttpStatus.OK) ;
    }

    @GetMapping( path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>( service.findById(id) ,HttpStatus.OK) ;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping( path = "/findByIdUser" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<SurveyUserDTO>> findByIdUser(@RequestParam(defaultValue = "0") int page ,@RequestParam(defaultValue = "10") int size , @RequestParam(defaultValue = "idSurvey") String order ,  @RequestParam(defaultValue = "false" , required = false) boolean asc  ) {
        Authentication  security =  SecurityContextHolder.getContext().getAuthentication();
        if( security.getPrincipal() == "ExpiredUser"){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Long idUser = ((User) security.getPrincipal()).getIdUser();
        if(asc){
            return ResponseEntity.ok( service.findPageableByUserId(idUser , PageRequest.of(page, size, Sort.by(order)))) ;
        }
        return  ResponseEntity.ok( service.findPageableByUserId(idUser , PageRequest.of(page, size, Sort.by(order).descending()))) ;
    }


    @PostMapping( path = "/findByCode")
    public ResponseEntity<?> findByCode(@RequestBody String code  ) {
        Authentication  security =  SecurityContextHolder.getContext().getAuthentication();
        
        var entity = service.findByCode(code);
        if (entity == null){
            return new ResponseEntity<>("notFound", HttpStatus.BAD_REQUEST);
        }

        if (!entity.isShowPublic()){
            return new ResponseEntity<>("notAvalible", HttpStatus.BAD_REQUEST);
        }

        if(entity.isRequiredLogged() && security.getPrincipal() == "anonymousUser" ){
            return new ResponseEntity<>("logged", HttpStatus.BAD_REQUEST);
        }

        if ( security.getPrincipal() =="ExpiredUser"){
            return  new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }


        return  ResponseEntity.ok(entity) ;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDto> create(@RequestBody SurveyRequest request){
        
        Authentication  security =  SecurityContextHolder.getContext().getAuthentication();
        Long idUser = ((User) security.getPrincipal()).getIdUser();
        
        return new ResponseEntity<>(service.create(request, idUser), HttpStatus.OK);
    }

    @PutMapping(path  = "/update/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurveyDto> update(@RequestBody SurveyRequest request , @PathVariable Long id){
        request.getSections().forEach(r -> {
            r.getQuestions().forEach(q -> {

                if (q.getOptions() != null){
                    q.getOptions().forEach(o -> {
                        System.out.println(o.isCorrect());
                    });
                }
            });
        });
        return new ResponseEntity<>(service.update(request , id) , HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}" )
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    
}

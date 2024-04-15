package com.formulario.encuesta.controller.advice;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.formulario.encuesta.common.exception.ControllerException;
import com.formulario.encuesta.common.exception.ResourceNotFoundException;
import com.formulario.encuesta.controller.payload.ApiResponse;

@RestControllerAdvice
public class ConfigControllerAdvice {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Map<String, String>> handleEmptyInput(ControllerException emptyInputException){
        return new ResponseEntity<Map<String, String>>(emptyInputException.getError(), emptyInputException.getErrorCode());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>  handleResponseNotFoundException(ResourceNotFoundException exception){
        ApiResponse  response = new ApiResponse(exception.getMessage() , "");
        return new ResponseEntity<>(response , HttpStatus.NOT_FOUND);
    }



}

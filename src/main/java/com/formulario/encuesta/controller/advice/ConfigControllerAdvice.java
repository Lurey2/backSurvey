package com.formulario.encuesta.controller.advice;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.formulario.encuesta.common.exception.ControllerException;

@ControllerAdvice
public class ConfigControllerAdvice {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<Map<String, String>> handleEmptyInput(ControllerException emptyInputException){
        return new ResponseEntity<Map<String, String>>(emptyInputException.getError(), emptyInputException.getErrorCode());
    }
}

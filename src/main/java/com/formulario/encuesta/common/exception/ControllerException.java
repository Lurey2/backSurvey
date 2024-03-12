package com.formulario.encuesta.common.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.formulario.encuesta.common.utils.builder.ErrorJsonBuilder;

public class ControllerException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    private HttpStatus errorCode;
    private ErrorJsonBuilder errorMessage;

  

    public ControllerException(HttpStatus errorCode, ErrorJsonBuilder errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorJsonBuilder getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorJsonBuilder errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getError(){
        return this.errorMessage.getError();       
    }
    


}

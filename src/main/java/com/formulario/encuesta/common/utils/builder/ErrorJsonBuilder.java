package com.formulario.encuesta.common.utils.builder;

import java.util.HashMap;
import java.util.Map;

public class ErrorJsonBuilder {
   
    private Map<String, String> jsonResponse = new HashMap<>();

    public ErrorJsonBuilder(){
    }

    public ErrorJsonBuilder(String fieldName , String message){
        this.jsonResponse.put(fieldName , message);
    }

    public Map<String, String> getError(){
        return this.jsonResponse;
    }
}

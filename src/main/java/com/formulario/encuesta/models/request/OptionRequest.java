package com.formulario.encuesta.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionRequest {
    
    private Long id;
    private String description;
    private boolean correct;
    private byte order;

}

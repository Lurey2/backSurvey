package com.formulario.encuesta.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionDto {

    private Long id;
    private String description;
    private boolean correct;
    private byte order;

}

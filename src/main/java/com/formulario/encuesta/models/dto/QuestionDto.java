package com.formulario.encuesta.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {
    
    private Long id;
    private String title;
    private String description;
    private boolean required;
    private String questionType;
    private byte order;
    private List<OptionDto> options;
}

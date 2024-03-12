package com.formulario.encuesta.models.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto {

    private Long id;
    private String title;
    private String description;
    private byte order;
    private List<QuestionDto> questions;
}

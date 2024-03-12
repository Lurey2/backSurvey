package com.formulario.encuesta.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerQuestionDto {

    private Long id;
    private String questionType;
    private String response;
    private List<OptionDto> check ;
}

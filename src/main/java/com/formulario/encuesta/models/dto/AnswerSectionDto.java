package com.formulario.encuesta.models.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSectionDto {
    
    private Long id;
    private List<AnswerQuestionDto> answerQuestions;

}

package com.formulario.encuesta.models.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {
    
    private Long idSurvey;
    private Long idPerson;
    private List<AnswerSectionRequest> answerSections;
    
}
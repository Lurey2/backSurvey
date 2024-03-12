package com.formulario.encuesta.models.request;

import java.util.List;

import com.formulario.encuesta.models.dto.QuestionDto;
import com.formulario.encuesta.models.dto.SectionDto;
import com.formulario.encuesta.models.entities.AnswerSection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSectionRequest {
    
    private Long id;
    private List<AnswerQuestionRequest> answerQuestions;
    private Long idSection;

}

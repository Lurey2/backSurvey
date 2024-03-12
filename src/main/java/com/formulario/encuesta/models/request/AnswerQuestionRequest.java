package com.formulario.encuesta.models.request;

import java.util.List;

import com.formulario.encuesta.models.dto.OptionDto;
import com.formulario.encuesta.models.dto.QuestionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerQuestionRequest {
    
    private Long id;
    private String questionType;
    private String response;
    private List<OptionDto> check ;
    private Long idQuestion;
}

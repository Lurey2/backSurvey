package com.formulario.encuesta.models.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.formulario.encuesta.models.entities.Answer;
import com.formulario.encuesta.models.entities.Survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyAnswerDTO {

    private Long idSurvey;
    private String documentName;
   
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime creation;
    
	@JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime modify;
    private boolean isShowPublic;
    private String previeImageURL;
    private String sharedCode;
    private Long countComplete;

   
}
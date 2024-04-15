package com.formulario.encuesta.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    
    private Long id;
    private List<AnswerSectionDto> answerSections; 
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createdAt;
}

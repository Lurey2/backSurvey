package com.formulario.encuesta.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyUserDTO {
    
    private Long id;
    private String title;
    private String previeImageURL;
    private String sharedCode;
    private String sharedEdit;

}

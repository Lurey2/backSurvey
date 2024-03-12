package com.formulario.encuesta.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyPublicDto {
        
    private Long id;
    private String title;
    private String previeImageURL;

}

package com.formulario.encuesta.models.dto;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyDto {

    private Long id;
    private String title;
    private String previeImageURL;
    private String sharedCode;
    private String sharedEdit;
    private boolean requiredLogged;
    private boolean score;
    private boolean showPublic;
    private boolean showEdit ;
    private boolean repeatForm;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserDto> sharedUsers ;
    private List<SectionDto> sections;

}

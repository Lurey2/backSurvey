package com.formulario.encuesta.models.request;



import java.util.List;

import com.formulario.encuesta.models.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyRequest {

    private String title;
    private String previeImageURL;
    private List<SectionRequest> sections;
    private boolean requiredLogged;
    private boolean score;
    private boolean showPublic;
    private boolean showEdit ;
    private boolean repeatForm;
    private List<UserDto> sharedUsers ;

}

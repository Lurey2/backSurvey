package com.formulario.encuesta.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.formulario.encuesta.models.dto.OptionDto;
import com.formulario.encuesta.models.dto.QuestionDto;
import com.formulario.encuesta.models.dto.SectionDto;
import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.entities.Section;
import com.formulario.encuesta.models.entities.Survey;
import com.formulario.encuesta.models.entities.User;

@Mapper(componentModel = "spring") 
public interface SurveyDtoMapper {

    
    @Mapping( source = "sharedUsers", target = "sharedUsers" , qualifiedByName = "userSharedMapping" )
    @Mapping( source = "idSurvey", target = "id" )
    @Mapping( source = "sections", target = "sections" , qualifiedByName = "sectionsMapping" )
    SurveyDto toDomain(Survey domain);

    @Named("sectionsMapping")
    default List<SectionDto> mapSections(List<Section> sections) {
        
        List<SectionDto> sectionDTO = new ArrayList<>();
        if ( sections != null){
            sections.forEach( sEntity -> {
                SectionDto  sectionDto = new SectionDto();

                sectionDto.setId(sEntity.getIdSection());
                sectionDto.setTitle(sEntity.getTitle());
                sectionDto.setDescription(sEntity.getTitle());
                sectionDto.setOrder(sEntity.getOrder());

                if ( sEntity.getQuestions() != null){
                    List<QuestionDto> questionDtoList =  new ArrayList<>();
    
                    sEntity.getQuestions().forEach(qEntity -> {
    
                        QuestionDto questionEntity = new QuestionDto();
                        questionEntity.setId(qEntity.getIdQuestion());
                        questionEntity.setTitle(qEntity.getTitle());
                        questionEntity.setDescription(qEntity.getDescription());
                        questionEntity.setQuestionType(qEntity.getQuestionType());
                        questionEntity.setOrder(qEntity.getOrder());
                        questionEntity.setRequired(qEntity.isRequired());
                        
                        if ( qEntity.getOptions() != null){
                            
                            
                            List<OptionDto> optionsDtoList = new ArrayList<>();

                            qEntity.getOptions().forEach(optDto -> {

                                OptionDto optionDto = new OptionDto();
                                optionDto.setId(optDto.getIdOption());
                                optionDto.setDescription(optDto.getDescription());
                                optionDto.setCorrect(optDto.isCorrect());
                                optionDto.setOrder(optDto.getOrder()); 

                                optionsDtoList.add(optionDto);
                            });

                            questionEntity.setOptions(optionsDtoList);
                        }
                        questionDtoList.add(questionEntity);
                        
                    } );
                    sectionDto.setQuestions(questionDtoList);
                };
                sectionDTO.add(sectionDto);
                
            });
        }
        return sectionDTO;
    }



    @Named("userSharedMapping")
    default List<UserDto> userSharedMapping(List<User> usersEntity) {
        
        List<UserDto> usersDto = new ArrayList<>();

        usersEntity.forEach(userEntity ->{
            usersDto.add(UserDto.builder().id(userEntity.getIdUser()).email(userEntity.getEmail()).build());
        });
        return usersDto;
    }
}

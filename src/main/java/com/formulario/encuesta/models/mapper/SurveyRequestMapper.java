package com.formulario.encuesta.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.formulario.encuesta.models.dto.UserDto;
import com.formulario.encuesta.models.entities.Option;
import com.formulario.encuesta.models.entities.Question;
import com.formulario.encuesta.models.entities.Section;
import com.formulario.encuesta.models.entities.Survey;
import com.formulario.encuesta.models.entities.User;
import com.formulario.encuesta.models.request.SectionRequest;
import com.formulario.encuesta.models.request.SurveyRequest;

@Mapper(componentModel = "spring") 
public interface SurveyRequestMapper {


    @Mapping( source = "sharedUsers", target = "sharedUsers" , qualifiedByName = "userSharedMapping" )
    @Mapping( source = "sections", target = "sections" , qualifiedByName = "sectionsMapping" )
    Survey toDomain(SurveyRequest request);

    

    @Named("sectionsMapping")
    default List<Section> mapSections(List<SectionRequest> sections) {
        
        List<Section> sectionsEntityList = new ArrayList<>();
        if ( sections != null){
            sections.forEach( sDto -> {
                Section  sectionEntity = new Section();

                sectionEntity.setIdSection(sDto.getId());
                sectionEntity.setTitle(sDto.getTitle());
                sectionEntity.setDescription(sDto.getTitle());
                sectionEntity.setOrder(sDto.getOrder());

                if ( sDto.getQuestions() != null){
                    List<Question> questionsEntityList =  new ArrayList<>();
    
                    sDto.getQuestions().forEach(qDto -> {
    
                        Question questionEntity = new Question();
                        questionEntity.setIdQuestion(qDto.getId());
                        questionEntity.setTitle(qDto.getTitle());
                        questionEntity.setDescription(qDto.getDescription());
                        questionEntity.setQuestionType(qDto.getQuestionType());
                        questionEntity.setOrder(qDto.getOrder());
                        questionEntity.setRequired(qDto.isRequired());
                        
                        if ( qDto.getOptions() != null){
                            
                            List<Option> optionsEntiyList = new ArrayList<>();

                            qDto.getOptions().forEach(optionDto -> {

                                Option optionEntity = new Option();
                                optionEntity.setIdOption(optionDto.getId());
                                optionEntity.setDescription(optionDto.getDescription());
                                optionEntity.setCorrect(optionDto.isCorrect());
                                optionEntity.setOrder(optionDto.getOrder()); 

                                optionsEntiyList.add(optionEntity);
                            });

                            questionEntity.setOptions(optionsEntiyList);
                        }
                        questionsEntityList.add(questionEntity);
                        
                    } );
                    sectionEntity.setQuestions(questionsEntityList);
                };
                sectionsEntityList.add(sectionEntity);
                
            });
        }
        return sectionsEntityList;
    }

    @Named("userSharedMapping")
    default List<User> userSharedMapping(List<UserDto> sharedUser) {
        
        List<User> users = new ArrayList<>();

        sharedUser.forEach(userDto ->{
            users.add(User.builder().idUser(userDto.getId()).build());
        });
        return users;
    }

}

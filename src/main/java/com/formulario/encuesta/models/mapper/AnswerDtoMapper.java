package com.formulario.encuesta.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.dto.AnswerQuestionDto;
import com.formulario.encuesta.models.dto.AnswerSectionDto;
import com.formulario.encuesta.models.dto.OptionDto;
import com.formulario.encuesta.models.entities.Answer;
import com.formulario.encuesta.models.entities.AnswerSection;

@Mapper(componentModel = "spring") 
public interface AnswerDtoMapper {
    
    @Mapping( source = "idAnswer", target = "id" )
    @Mapping( source = "answerSections", target = "answerSections" , qualifiedByName = "sectionsMapping" )
    AnswerDto toDomain(Answer answer);

    @Named("sectionsMapping")
    default List<AnswerSectionDto> mapSections(List<AnswerSection> sections) {
        
        List<AnswerSectionDto> sectionDTOList = new ArrayList<>();
        if ( sections != null){
            sections.forEach( sEntity -> {
                AnswerSectionDto  sectionDto = new AnswerSectionDto();
                sectionDto.setId(sEntity.getIdAnswerSection());


                if ( sEntity.getAnswerQuestions() != null){
                    List<AnswerQuestionDto> questionDtoList =  new ArrayList<>();
    
                    sEntity.getAnswerQuestions().forEach(qEntity -> {
    
                        AnswerQuestionDto questionEntity = new AnswerQuestionDto();
                        
                        questionEntity.setId(qEntity.getIdAnswerQuestion());
                        questionEntity.setResponse(qEntity.getResponse());
                        questionEntity.setQuestionType(qEntity.getQuestionType());


                        if ( qEntity.getCheck() != null){
                            
                            List<OptionDto> optionsDtoList = new ArrayList<>();

                            qEntity.getCheck().forEach(optDto -> {

                                OptionDto optionDto = new OptionDto();
                                optionDto.setId(optDto.getIdOption());
                                optionDto.setDescription(optDto.getDescription());
                                optionDto.setCorrect(optDto.isCorrect());
                                optionDto.setOrder(optDto.getOrder()); 

                                optionsDtoList.add(optionDto);
                            });

                            questionEntity.setCheck(optionsDtoList);
                        }
                        questionDtoList.add(questionEntity);
                        
                    } );
                   sectionDto.setAnswerQuestions(questionDtoList);
                };
                sectionDTOList.add(sectionDto);
            });
        }
        return sectionDTOList;
    }
}

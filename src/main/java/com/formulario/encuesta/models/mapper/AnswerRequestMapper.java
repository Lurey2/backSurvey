package com.formulario.encuesta.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.formulario.encuesta.models.entities.Answer;
import com.formulario.encuesta.models.entities.AnswerQuestion;
import com.formulario.encuesta.models.entities.AnswerSection;
import com.formulario.encuesta.models.entities.Option;
import com.formulario.encuesta.models.entities.Question;
import com.formulario.encuesta.models.entities.Section;
import com.formulario.encuesta.models.entities.Survey;
import com.formulario.encuesta.models.request.AnswerRequest;
import com.formulario.encuesta.models.request.AnswerSectionRequest;
@Mapper(componentModel = "spring") 
public interface AnswerRequestMapper {
    
    @Mapping( target =  "createdAt" , ignore = true)
    @Mapping( target =  "idAnswer" , ignore = true)
    @Mapping( source = "idSurvey", target = "survey" , qualifiedByName = "setSurvey")
    @Mapping( source = "answerSections", target = "answerSections" , qualifiedByName = "sectionsMapping" )
    Answer toDomain(AnswerRequest request);

    @Named("sectionsMapping")
    default List<AnswerSection> mapSections(List<AnswerSectionRequest> sections) {
        
        List<AnswerSection> sectionsEntityList = new ArrayList<>();
        if ( sections != null){
            sections.forEach( sDto -> {
                AnswerSection  sectionEntity = new AnswerSection();
                
                Section section = new Section();
                section.setIdSection(sDto.getIdSection());
                sectionEntity.setSection(section);

                if ( sDto.getAnswerQuestions() != null){
                    List<AnswerQuestion> questionsEntityList =  new ArrayList<>();
    
                    sDto.getAnswerQuestions().forEach(qDto -> {
    
                        AnswerQuestion questionEntity = new AnswerQuestion();
                        questionEntity.setResponse(qDto.getResponse());
                        questionEntity.setQuestionType(qDto.getQuestionType());

                        Question question = new Question();
                        question.setIdQuestion(qDto.getIdQuestion());
                        questionEntity.setQuestion(question);
                        
                        if ( qDto.getCheck() != null){
                            
                            List<Option> optionsEntiyList = new ArrayList<>();

                            qDto.getCheck().forEach(optionDto -> {

                                Option optionEntity = new Option();
                                optionEntity.setIdOption(optionDto.getId());
                                optionEntity.setDescription(optionDto.getDescription());
                                optionEntity.setCorrect(optionDto.isCorrect());
                                optionEntity.setOrder(optionDto.getOrder()); 

                                optionsEntiyList.add(optionEntity);
                            });

                            questionEntity.setCheck(optionsEntiyList);
                        }
                        questionsEntityList.add(questionEntity);
                        
                    } );
                    sectionEntity.setAnswerQuestions(questionsEntityList);
                };
                sectionsEntityList.add(sectionEntity);
                
            });
        }
        return sectionsEntityList;
    }

    
    @Named("setSurvey")
    default Survey setUser(Long id) {
        var survey = new Survey();
        survey.setIdSurvey(id);
        return survey;
    }
}

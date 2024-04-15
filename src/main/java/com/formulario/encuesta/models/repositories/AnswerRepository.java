package com.formulario.encuesta.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formulario.encuesta.models.dto.AnswerDto;
import com.formulario.encuesta.models.entities.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    @Query("SELECT answer FROM Answer answer where answer.survey.idSurvey = :idSurvey ")
    List<Answer> findByIdSurvey(@Param("idSurvey") Long idSurvey);
}

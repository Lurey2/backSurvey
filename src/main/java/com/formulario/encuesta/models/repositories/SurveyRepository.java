package com.formulario.encuesta.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formulario.encuesta.models.dto.SurveyAnswerDTO;
import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.SurveyUserDTO;
import com.formulario.encuesta.models.entities.Survey;
import java.util.List;


@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    

    @Query("select NEW com.formulario.encuesta.models.dto.SurveyUserDTO(idSurvey, title,previeImageURL,sharedCode,sharedEdit) from Survey s where  s.user.idUser = :idUser ")
    Page<SurveyUserDTO> findPageableByUserId(@Param("idUser") Long idUser , Pageable pageable );

    Survey  findBySharedCode(String sharedCode);

    @Query("SELECT NEW com.formulario.encuesta.models.dto.SurveyAnswerDTO(survey.idSurvey , survey.title , survey.createdAt , survey.updatedAt , survey.showPublic , survey.previeImageURL , survey.sharedCode , COUNT(answer.idAnswer)) FROM  Survey survey LEFT JOIN Answer answer ON answer.survey.idSurvey = survey.idSurvey WHERE survey.user.idUser = :idUser  GROUP BY survey.idSurvey  ")
    Page<SurveyAnswerDTO> findSurveyAnswerByUser(@Param("idUser") Long idUser , Pageable pageable);
    
    @Query("SELECT NEW com.formulario.encuesta.models.dto.SurveyAnswerDTO(survey.idSurvey , survey.title , survey.createdAt , survey.updatedAt , survey.showPublic , survey.previeImageURL , survey.sharedCode , COUNT(answer.idAnswer)) FROM  Survey survey LEFT JOIN Answer answer ON answer.survey.idSurvey = survey.idSurvey WHERE survey.user.idUser = :idUser  AND  LOWER(survey.title) LIKE  CONCAT('%' , LOWER(:name) , '%' )  GROUP BY survey.idSurvey  ")
    Page<SurveyAnswerDTO> findSurveyAnswerByUserAndFilter(@Param("idUser") Long idUser , @Param("name") String name , Pageable pageable);
}

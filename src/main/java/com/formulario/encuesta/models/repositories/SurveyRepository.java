package com.formulario.encuesta.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.formulario.encuesta.models.dto.SurveyDto;
import com.formulario.encuesta.models.dto.SurveyUserDTO;
import com.formulario.encuesta.models.entities.Survey;
import java.util.List;


@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    

    @Query("select NEW com.formulario.encuesta.models.dto.SurveyUserDTO(idSurvey, title,previeImageURL,sharedCode,sharedEdit) from Survey s where  s.user.idUser = :idUser ")
    Page<SurveyUserDTO> findPageableByUserId(@Param("idUser") Long idUser , Pageable pageable );

    Survey  findBySharedCode(String sharedCode);
}

package com.formulario.encuesta.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formulario.encuesta.models.entities.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}

package com.formulario.encuesta.models.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnswerQuestion {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnswerQuestion;

    @Column(nullable = false , length = 50)
    private String questionType;

    @Column(nullable = true , length = 500)
    private String response;

    @ManyToMany(cascade =  CascadeType.REFRESH , fetch = FetchType.EAGER  )
    private List<Option> check ;

   
    @JoinColumn(nullable = false , name = "id_question" , referencedColumnName = "idQuestion")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Question question;

    @JoinColumn(nullable = false , name = "id_answer_section" , referencedColumnName = "idAnswerSection")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private AnswerSection answerSection;
}

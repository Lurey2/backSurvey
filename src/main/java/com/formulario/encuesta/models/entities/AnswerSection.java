package com.formulario.encuesta.models.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnswerSection {
      
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnswerSection;

    @JoinColumn(nullable = false , name = "id_answer" , referencedColumnName = "idAnswer")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Answer answer;

    @JoinColumn(nullable = false , name = "id_section" , referencedColumnName = "idSection")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Section section;


    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "answerSection" ,  fetch = FetchType.EAGER)
    private List<AnswerQuestion> answerQuestions;

    public void setAnswerQuestions(List<AnswerQuestion> answerOptions){
        answerOptions.forEach((f) -> {
            f.setAnswerSection(this);
        });
        this.answerQuestions = answerOptions;
    }
    
}

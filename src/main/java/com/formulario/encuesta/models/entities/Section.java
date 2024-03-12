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
public class Section {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSection;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true , length = 500)
    private String description;

    @Column(name = "order_position" , nullable = false)
    private byte order;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL ,  mappedBy = "section" , fetch = FetchType.EAGER)
    private List<Question> questions;

    @JoinColumn(nullable = false , name = "id_survey" , referencedColumnName = "idSurvey")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Survey survey;

    public void setQuestions(List<Question> questions){
        questions.forEach((f) -> {
            f.setSection(this);
        });
        this.questions = questions;
    }


}

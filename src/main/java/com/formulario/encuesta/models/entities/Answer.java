package com.formulario.encuesta.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
public class Answer {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnswer;

    @Column(nullable = true)
    private Long idPerson;

    @CreationTimestamp
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime createdAt;

    @JoinColumn(nullable = false, name = "id_survey", referencedColumnName = "idSurvey")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Survey survey;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "answer", fetch = FetchType.EAGER , orphanRemoval = true) 
    private List<AnswerSection> answerSections;

    public void setAnswerSections(List<AnswerSection> answerSections) {
        answerSections.forEach((f) -> {
            f.setAnswer(this);
        });
        this.answerSections = answerSections;
    }

}

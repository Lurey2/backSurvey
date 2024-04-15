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
public class Question {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestion;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true , length = 500)
    private String description;

    @Column(nullable = false)
    private boolean required;

    @Column(nullable = false , length = 50)
    private String questionType;

    @Column(nullable = false , name = "order_position" )
    private byte order;

    
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "question" ,  fetch = FetchType.EAGER , orphanRemoval = true)
    private List<Option> options;


    @JoinColumn(nullable = false , name = "id_section")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Section section;


    public void setOptions(List<Option> options){
        options.forEach((f) -> {
            f.setQuestion(this);
        });
        this.options = options;
    }
}

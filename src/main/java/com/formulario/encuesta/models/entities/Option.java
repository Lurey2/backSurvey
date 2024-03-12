package com.formulario.encuesta.models.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name =  "options")
public class Option {
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOption;

    @Column(nullable = false , length = 300)
    private String description;

    
    @Column(nullable =  false )
    private boolean correct ;

    
    @Column(name = "order_position" , nullable = false )
    private byte order;

    @JoinColumn(nullable = false , name = "id_question")
    @ManyToOne(fetch = FetchType.LAZY )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private Question question;
    
}

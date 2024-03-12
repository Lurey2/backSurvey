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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
public class Survey {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSurvey;

    @Column(nullable = false , length = 250)
    private String title;

    @Column(nullable = false , length = 500)
    private String previeImageURL;

    @Column(nullable = false )
    private boolean state;

    @Column(nullable = false , updatable = false )
    private String sharedCode;

    @Column(nullable = false  , updatable = false)
    private String sharedEdit;

    @Column(nullable = false )
    private boolean requiredLogged;

    @Column(nullable = false )
    private boolean score;

    @Column(nullable = false )
    private boolean showPublic ;
    
    @Column(nullable = false )
    private boolean showEdit ;
    
    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL ,  mappedBy = "survey" , fetch = FetchType.EAGER)
    private List<Section> sections;

    @ManyToMany(cascade =  CascadeType.REFRESH , fetch = FetchType.EAGER  )
    private List<User> sharedUsers ;

    @JoinColumn(nullable = false , name =  "id_user" , updatable = false)
    @ManyToOne(fetch = FetchType.EAGER  )
    @OnDelete(action =  OnDeleteAction.CASCADE)
    private User user ;


    
    public void setSections(List<Section> sections){
        sections.forEach((f) -> {
            f.setSurvey(this);
        });
        this.sections = sections;
    }

    
}

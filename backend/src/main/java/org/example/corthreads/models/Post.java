package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table (name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // l'id sera générer automatiquement
    @Column(length = 36)
    private String idPost ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="idUser" , referencedColumnName = "idUser" , nullable=false ,insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Utilisateur utilisateur;

    @Column(name ="idUser", nullable = false)
    private String idUser ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePost typePost ;

    @Enumerated(EnumType.STRING)
    private TypeDon typeDon ;

    @Enumerated(EnumType.STRING)
    private GroupeSang groupSang ;

    @Enumerated(EnumType.STRING)
    private Rh rh ;

    @Enumerated(EnumType.STRING)
    private Urgence urgence ;

    private String contenu ;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePublication ;

    @Column(nullable = false)
    private String wilaya ;
}

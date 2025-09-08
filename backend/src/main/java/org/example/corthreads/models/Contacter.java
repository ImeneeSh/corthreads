package org.example.corthreads.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="contacter")
public class Contacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idContacter")
    private int idContacter ;

    @Column(name="idUser")
    private String idUser ;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="email")
    private String email;

    @Column(name="contenu")
    @Lob
    private String contenu;

}

package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity // indique que cette classe est une table en bdd
@Data //genère les getters + setters automatiquement
@Table (name ="utilisateur")
public class Utilisateur {

    @Id // pour indiquer que c'est une clé primaire
    @Column(name = "idUser")
    private String idUser ;

    @Column(nullable = false ) // champ obligatoire
    private String nom ;

    @Column(nullable = false )
    private String prenom ;

    @Column(nullable = false )
    private String wilaya ;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance ; //LocalDate correspond au DATE dans sql

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role ;

    @Column(nullable = false)
    private String mdp ; // il sera haché

    @Enumerated(EnumType.STRING)
    private GroupeSang groupeSang;

    private Rh rh;

    private String specialite ;

    private String etablissement ;

    @Column(name = "estDonneurOrgane") // on fait ça quand le nom dans la bdd diffère de celui en java
    private boolean donneurOrgane ;

    @Column(name = "estDonneurFoie")
    private boolean donneurFoie ;

    @Column(name = "estDonneurSang")
    private boolean donneurSang ;

    @Column(name = "dateDerniereModification")
    private LocalDate dateDerniereModification;

    @Column(name = "dernierDonSang")
    private LocalDate dernierDonSang;

}



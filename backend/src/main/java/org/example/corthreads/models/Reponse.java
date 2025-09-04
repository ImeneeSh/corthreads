package org.example.corthreads.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reponsesutilisateur")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRep ;

    private Integer idQstGen ;

    private Integer idQstRisk;

    private Integer idQstAntc ;

    private String idUser ;

    private boolean valeur ;
}

package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table( name ="inscriptionfoie")
public class InscrFoie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscrFoie;

    @Column(name= "idUser" , nullable = false)
    private String idUser ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser" , referencedColumnName = "idUser" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private Utilisateur utilisateur;

    @Column (name = "dateVisiteProgrammer")
    private LocalDate dateVisiteProgrammer;
}

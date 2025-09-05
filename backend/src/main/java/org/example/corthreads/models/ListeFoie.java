package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table ( name="listefoie")
public class ListeFoie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idListeFoie;

    @Column(name = "idUser" , nullable = false)
    private String idUser ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser" , referencedColumnName = "idUser" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private Utilisateur utilisateur;

    @Column(name = "idInscrFoie" , nullable= false)
    private int  idInscrFoie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idInscrFoie" , referencedColumnName = "idInscrFoie" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private InscrFoie inscrFoie;
}

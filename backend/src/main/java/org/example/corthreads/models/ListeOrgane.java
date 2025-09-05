package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table ( name="listeorgane")
public class ListeOrgane {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idListeOrgane;

    @Column(name = "idUser" , nullable = false)
    private String idUser ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser" , referencedColumnName = "idUser" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private Utilisateur utilisateur;
}

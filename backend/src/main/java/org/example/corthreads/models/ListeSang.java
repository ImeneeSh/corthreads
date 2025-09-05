package org.example.corthreads.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table ( name="listesang")
public class ListeSang {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idListeSang;

    @Column(name = "idUser" , nullable = false)
    private String idUser ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser" , referencedColumnName = "idUser" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private Utilisateur utilisateur;

    @Column(name = "idInscrSang" , nullable= false)
    private int  idInscrSang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idInscrSang" , referencedColumnName = "idInscrSang" , insertable = false , updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"})
    private InscrSang inscrSang;
}

package org.example.corthreads.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="qstgen")
public class QstGen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String idQstGen ;

    @Lob
    private String contenu ;
}

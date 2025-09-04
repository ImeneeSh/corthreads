package org.example.corthreads.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="qstantc")
public class QstAntc {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String idQstAntc ;

    @Lob
    private String contenu ;
}

package org.example.corthreads.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name="qstrisk")
public class QstRisk {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 36)
    private String idQstRisk ;

    @Lob
    private String contenu ;
}

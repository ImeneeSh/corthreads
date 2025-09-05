package org.example.corthreads.repositories;

import org.example.corthreads.models.InscrFoie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscrFoieRepository extends JpaRepository<InscrFoie,Integer> {

}

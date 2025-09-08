package org.example.corthreads.repositories;

import org.example.corthreads.models.Contacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ContacterRepository extends JpaRepository<Contacter,Integer> {
}

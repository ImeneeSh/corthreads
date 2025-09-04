package org.example.corthreads.repositories;

import org.example.corthreads.models.QstAntc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QstAntcRepository extends JpaRepository<QstAntc,String> {
}

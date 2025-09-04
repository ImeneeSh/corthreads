package org.example.corthreads.repositories;

import org.example.corthreads.models.QstRisk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QstRiskRepository extends JpaRepository<QstRisk,String> {
}

package org.example.corthreads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.corthreads.models.QstGen ;

@Repository
public interface QstGenRepository extends JpaRepository<QstGen,String> {
}

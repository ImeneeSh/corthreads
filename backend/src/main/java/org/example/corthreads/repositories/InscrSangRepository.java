package org.example.corthreads.repositories;

import org.example.corthreads.models.InscrSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscrSangRepository extends JpaRepository<InscrSang,Integer> {

}

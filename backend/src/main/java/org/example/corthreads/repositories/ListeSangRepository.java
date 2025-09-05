package org.example.corthreads.repositories;

import org.example.corthreads.models.ListeSang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeSangRepository extends JpaRepository<ListeSang, Integer> {
}

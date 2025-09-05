package org.example.corthreads.repositories;

import org.example.corthreads.models.ListeOrgane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeOrganeRepository extends JpaRepository<ListeOrgane, Integer> {
}

package org.example.corthreads.repositories;

import org.example.corthreads.models.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Integer> {

    List <Reponse> findByIdUser(String idUser);

    List<Reponse> findByIdUserAndIdQstGen(String idUser, int idQstGen);

    List<Reponse> findByIdUserAndIdQstRisk(String idUser, int idQstRisk);

    List<Reponse> findByIdUserAndIdQstAntc(String idUser, int idQstAntc);

}

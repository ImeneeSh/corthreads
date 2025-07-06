package org.example.corthreads.repositories;

import dtos.CritereRechercheMatchs;
import dtos.MatchsDetails;
import models.Matchs;

import java.util.List;

public interface MatchsRepo {

    Matchs findMatchs(String idMatch);
    void saveMatchs(Matchs matchs);
    void suppMatchs(String idMatch);

    MatchsDetails findMatchsDetails(String idMatch);

    List<Matchs> findAllMatchs(int page, int pageSize);
    List<Matchs> findAllMatchs();

    List<Matchs> rechercherMatchs(CritereRechercheMatchs critere);
    List<Matchs> rechercherMatchs(CritereRechercheMatchs critere, int page, int pageSize);
}
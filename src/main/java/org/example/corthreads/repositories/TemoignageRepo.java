package org.example.corthreads.repositories;

import dtos.CritereRechercheTemoignage;
import dtos.TemoignageDetails;
import models.Temoignage;

import java.util.List;

public interface TemoignageRepo {

    Temoignage findTemoignage(String idTemoignage);
    void saveTemoignage(Temoignage temoignage);
    void suppTemoignage(String idTemoignage);

    TemoignageDetails findTemoignageDetails(String idTemoignage);

    List<Temoignage> findAllTemoignages(int page, int pageSize);
    List<Temoignage> findAllTemoignages();

    List<Temoignage> rechercherTemoignage(CritereRechercheTemoignage critere);
    List<Temoignage> rechercherTemoignage(CritereRechercheTemoignage critere, int page, int pageSize);
}
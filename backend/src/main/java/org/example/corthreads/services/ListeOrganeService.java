package org.example.corthreads.services;

import org.example.corthreads.models.ListeOrgane;
import org.example.corthreads.repositories.ListeOrganeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeOrganeService {

    private final ListeOrganeRepository listeOrganeRepository;

    public ListeOrganeService(ListeOrganeRepository listeOrganeRepository) {
        this.listeOrganeRepository = listeOrganeRepository;
    }

    public List<ListeOrgane> getAllListeOrgane() {
        return listeOrganeRepository.findAll() ;
    }

    public ListeOrgane ajoutListeOrgane(ListeOrgane listeOrgane) {
        return listeOrganeRepository.save(listeOrgane);
    }
}

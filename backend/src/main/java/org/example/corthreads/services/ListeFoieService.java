package org.example.corthreads.services;

import org.example.corthreads.models.ListeFoie;
import org.example.corthreads.repositories.ListeFoieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeFoieService {

    private final ListeFoieRepository listeFoieRepository;

    public ListeFoieService(ListeFoieRepository listeFoieRepository) {
        this.listeFoieRepository = listeFoieRepository;
    }

    public List<ListeFoie> getAllListeFoie() {
        return listeFoieRepository.findAll() ;
    }

    public ListeFoie ajoutListeFoie(ListeFoie listeFoie) {
        return listeFoieRepository.save(listeFoie);
    }

    public void supprimerListeFoieId(int idListeFoie) {
        if(!listeFoieRepository.existsById(idListeFoie)){
            throw new IllegalArgumentException("Ce post de sang n'existe pas");
        }
        listeFoieRepository.deleteById(idListeFoie);
    }
}

package org.example.corthreads.services;

import org.example.corthreads.models.InscrFoie;
import org.example.corthreads.repositories.InscrFoieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscrFoieService {

    private final InscrFoieRepository inscrFoieRepository;

    public InscrFoieService(InscrFoieRepository repo) {
        this.inscrFoieRepository = repo;
    }

    public List<InscrFoie> getAllInscrFoie() {
        return inscrFoieRepository.findAll();
    }

    public InscrFoie ajoutInscrFoie(InscrFoie inscrFoie) {
        return inscrFoieRepository.save(inscrFoie);
    }

}

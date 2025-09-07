package org.example.corthreads.services;

import org.example.corthreads.models.Etat;
import org.example.corthreads.models.InscrFoie;
import org.example.corthreads.models.InscrSang;
import org.example.corthreads.repositories.InscrFoieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void supprimerInscrFoieId(int idInscrFoie) {
        if(!inscrFoieRepository.existsById(idInscrFoie)){
            throw new IllegalArgumentException("Ce post de sang n'existe pas");
        }
        inscrFoieRepository.deleteById(idInscrFoie);
    }

    public InscrFoie modifierEtatInscrFoie(int idInscrFoie , Etat etat, LocalDate date) {
        InscrFoie inscrfoie = inscrFoieRepository.findById(idInscrFoie).orElseThrow(() -> new IllegalArgumentException("Non trouvée !!"));

        if(etat != null){
            inscrfoie.setEtat(etat);
        }

        if(date != null){
            inscrfoie.setDateVisiteProgrammer(date);
        }
        return inscrFoieRepository.save(inscrfoie);
    }

}

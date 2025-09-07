package org.example.corthreads.services;

import org.example.corthreads.models.Etat;
import org.example.corthreads.models.InscrSang;
import org.example.corthreads.repositories.InscrSangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscrSangService {

    private final InscrSangRepository inscrSangRepository;

    public InscrSangService(InscrSangRepository repo) {
        this.inscrSangRepository = repo;
    }

    public List<InscrSang> getAllInscrSang() {
        return inscrSangRepository.findAll();
    }

    public InscrSang ajoutInscrSang(InscrSang inscrSang) {
        return inscrSangRepository.save(inscrSang);
    }

    public void supprimerInscrSangId(int idInscrSang) {
        if(!inscrSangRepository.existsById(idInscrSang)){
            throw new IllegalArgumentException("Ce post de sang n'existe pas");
        }
        inscrSangRepository.deleteById(idInscrSang);
    }

    public InscrSang modifierEtatInscrSang(int idInscrSang , Etat etat) {
        InscrSang inscrsang = inscrSangRepository.findById(idInscrSang).orElseThrow(() -> new IllegalArgumentException("Non trouvée !!" + idInscrSang));

        inscrsang.setEtat(etat);
        return inscrSangRepository.save(inscrsang);
    }

}

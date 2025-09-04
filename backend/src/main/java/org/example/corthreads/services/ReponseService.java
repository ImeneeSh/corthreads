package org.example.corthreads.services;

import org.example.corthreads.models.Reponse;
import org.example.corthreads.repositories.ReponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReponseService {

    private final ReponseRepository reponseRepository;

    public ReponseService(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

    public Reponse ajoutReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    public List<Reponse> getReponsesUtilisateur(String idUser) {
        return reponseRepository.findByIdUser(idUser);
    }

    public List<Reponse> getReponsesParQst(String idUser , String type ,int idQst) {
        return switch(type.toLowerCase()) {
            case "gen" ->  reponseRepository.findByIdUserAndIdQstGen(idUser , idQst);
            case "risk" ->  reponseRepository.findByIdUserAndIdQstRisk(idUser , idQst);
            case "antc" ->  reponseRepository.findByIdUserAndIdQstAntc(idUser , idQst);
            default -> throw new IllegalArgumentException("Type de question invalide :" +type);
        };
    }
}

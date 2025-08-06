package org.example.corthreads.services;

import org.example.corthreads.dto.AuthReponse;
import org.example.corthreads.models.Utilisateur;
import org.example.corthreads.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder ;
import org.springframework.stereotype.Service ;

@Service
public class AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthReponse authentifier(String idUser , String mdp){
        Utilisateur utilisateur = utilisateurRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Utilisateur non trouve !"));

        if(!passwordEncoder.matches(mdp,utilisateur.getMdp())){
            throw new RuntimeException("Mot de passe incorrect !!");
        }

        return new AuthReponse(
                utilisateur.getIdUser(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getRole()
        );
    }
}

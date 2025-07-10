package org.example.corthreads.services;

import org.example.corthreads.models.GroupeSang;
import org.example.corthreads.models.Utilisateur ;
import org.example.corthreads.models.Role ;
import org.example.corthreads.models.Genre ;
import org.example.corthreads.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        // hasher le mdp
        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));

        if(utilisateur.getRole()== null){
            utilisateur.setRole(Role.Citoyen);
        }

        return  utilisateurRepository.save(utilisateur);
    }

    public Utilisateur trouverParEmail(String email){
        return utilisateurRepository.findById(email).orElse(null);
    }

    public List<Utilisateur> rechercherUtilisateurs(String nom,String prenom , String wilaya, Genre genre, Role role , GroupeSang groupeSang){
        if((nom != null) && (prenom != null)){
            return utilisateurRepository.findByNomAndPrenom(nom,prenom);
        }else if((nom != null) && (prenom == null)){
            return  utilisateurRepository.findByNom(nom);
        }else if((nom == null)&&(prenom != null)){
            return utilisateurRepository.findByPrenom(prenom);
        }
        else if (wilaya != null) {
            return utilisateurRepository.findByWilaya(wilaya);
        } else if (genre != null) {
            return utilisateurRepository.findByGenre(genre);
        } else if (role != null) {
            return utilisateurRepository.findByRole(role);
        }else if(groupeSang != null) {
            return utilisateurRepository.findByGroupeSang(groupeSang);
        } else {
            return utilisateurRepository.findAll();
        }
    }

}

package org.example.corthreads.services;

import org.example.corthreads.models.*;
import org.example.corthreads.repositories.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Utilisateur modifierUtilisateurId(String idUser, String nom, String prenom, String wilaya, LocalDate dateNaissance, Genre genre, String mdp, GroupeSang groupeSang, Rh rh, String specialite, String etablissement,Boolean donneurOrgane, Boolean donneurFoie,Boolean donneurSang, LocalDate dernierDonSang,LocalDate dateDerniereModification){
        Utilisateur utilisateur =utilisateurRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Le utilisateur n'existe pas"));

        if(nom != null){
            utilisateur.setNom(nom);
        }

        if(prenom != null){
            utilisateur.setPrenom(prenom);
        }

        if(wilaya != null){
            utilisateur.setWilaya(wilaya);
        }

        if(genre != null){
            utilisateur.setGenre(genre);
        }

        if(mdp != null){
            utilisateur.setMdp(passwordEncoder.encode(mdp));
        }

        if(dateNaissance != null){
            utilisateur.setDateNaissance(dateNaissance);
        }

        if(groupeSang != null){
            utilisateur.setGroupeSang(groupeSang);
        }

        if(rh != null){
            utilisateur.setRh(rh);
        }

        if(specialite != null){
            utilisateur.setSpecialite(specialite);
        }

        if(etablissement != null){
            utilisateur.setEtablissement(etablissement);
        }

        if(donneurOrgane != null){
            utilisateur.setDonneurOrgane(donneurOrgane);
        }

        if(donneurFoie != null){
            utilisateur.setDonneurFoie(donneurFoie);
        }

        if(donneurSang != null){
            utilisateur.setDonneurSang(donneurSang);
        }

        if(dernierDonSang != null){
            utilisateur.setDernierDonSang(dernierDonSang);
        }

        if(dateDerniereModification != null){
            utilisateur.setDateDerniereModification(dateDerniereModification);
        }

        return  utilisateurRepository.save(utilisateur);
    }

}

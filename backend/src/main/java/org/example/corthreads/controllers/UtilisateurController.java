package org.example.corthreads.controllers;

import org.example.corthreads.models.*;
import org.example.corthreads.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/inscription") // pour définir le type de requete http
    public Utilisateur inscrireUtilisateur(@RequestBody Utilisateur utilisateur) { //convertir le JSON en objet utilisateur
        return utilisateurService.inscrireUtilisateur(utilisateur);
    }

    @GetMapping("/{email}")
    public Utilisateur trouveParMail(@PathVariable String email) {
        return utilisateurService.trouverParEmail(email);
    }

    @GetMapping("/recherche")
    public List<Utilisateur> rechercher(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String wilaya,
            @RequestParam(required = false) Genre genre,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) GroupeSang groupeSang) {

        return utilisateurService.rechercherUtilisateurs(nom, prenom, wilaya, genre, role, groupeSang);
    }

    @PutMapping("modifier/{idUser}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable String idUser, @RequestParam(required = false) String nom,  @RequestParam(required = false) String prenom, @RequestParam(required = false) String wilaya, @RequestParam(required = false) LocalDate dateNaissance, @RequestParam(required = false) Genre genre, @RequestParam(required = false) String mdp, @RequestParam(required = false) GroupeSang groupeSang, @RequestParam(required = false) Rh rh, @RequestParam(required = false) String specialite, @RequestParam(required = false) String etablissement, @RequestParam(required = false) Boolean donneurOrgane, @RequestParam(required = false) Boolean donneurFoie, @RequestParam(required = false) Boolean donneurSang, @RequestParam(required = false) LocalDate dernierDonSang, @RequestParam(required = false) LocalDate dateDerniereModification){

        Utilisateur updateUtil = utilisateurService.modifierUtilisateurId(idUser,nom,prenom,wilaya,dateNaissance,genre,mdp,groupeSang,rh,specialite,etablissement,donneurOrgane,donneurFoie,donneurSang,dernierDonSang,dateDerniereModification);
        return ResponseEntity.ok(updateUtil);
    }


}

package org.example.corthreads.controllers;

import org.example.corthreads.models.Genre;
import org.example.corthreads.models.GroupeSang;
import org.example.corthreads.models.Role;
import org.example.corthreads.models.Utilisateur ;
import org.example.corthreads.services.UtilisateurService;
import org.springframework.web.bind.annotation.*;
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


}

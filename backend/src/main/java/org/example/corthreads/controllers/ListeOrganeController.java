package org.example.corthreads.controllers;

import org.example.corthreads.models.ListeOrgane;
import org.example.corthreads.services.ListeOrganeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listeorgane")
public class ListeOrganeController {

    private final ListeOrganeService listeOrganeService ;

    public ListeOrganeController(ListeOrganeService listeOrganeService) {
        this.listeOrganeService = listeOrganeService;
    }

    @PostMapping("ajout")
    public ListeOrgane ajouterListeOrgane(@RequestBody ListeOrgane ListeOrgane) {
        return listeOrganeService.ajoutListeOrgane(ListeOrgane);
    }

    @GetMapping
    public List<ListeOrgane> getAllListeOrgane() {
        return listeOrganeService.getAllListeOrgane() ;
    }

    @DeleteMapping("/suppression/{idListeOrgane}")
    public ResponseEntity<String> supprimerListeOrgane(@PathVariable int idListeOrgane) {
        listeOrganeService.supprimerListeOrganeId(idListeOrgane);
        return ResponseEntity.ok("Post supprimé avec succès !");
    }
}

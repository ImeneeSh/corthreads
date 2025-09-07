package org.example.corthreads.controllers;

import org.example.corthreads.models.ListeFoie;
import org.example.corthreads.services.ListeFoieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listefoie")
public class ListeFoieController {

    private final ListeFoieService listeFoieService ;

    public ListeFoieController(ListeFoieService listeFoieService) {
        this.listeFoieService = listeFoieService;
    }

    @PostMapping("ajout")
    public ListeFoie ajouterListeFoie(@RequestBody ListeFoie ListeFoie) {
        return listeFoieService.ajoutListeFoie(ListeFoie);
    }

    @GetMapping
    public List<ListeFoie> getAllListeFoie() {
        return listeFoieService.getAllListeFoie() ;
    }

    @DeleteMapping("/suppression/{idListeFoie}")
    public ResponseEntity<String> supprimerListeFoie(@PathVariable int idListeFoie) {
        listeFoieService.supprimerListeFoieId(idListeFoie);
        return ResponseEntity.ok("Post supprimé avec succès !");
    }
}

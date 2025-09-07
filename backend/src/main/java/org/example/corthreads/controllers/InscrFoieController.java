package org.example.corthreads.controllers;

import org.example.corthreads.models.Etat;
import org.example.corthreads.models.InscrFoie;
import org.example.corthreads.services.InscrFoieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/incrfoie")
public class InscrFoieController {

    private final InscrFoieService inscrFoieService;

    public InscrFoieController(InscrFoieService inscrFoieService) {
        this.inscrFoieService = inscrFoieService;
    }

    @PostMapping("ajout")
    public InscrFoie ajouterInscrSang(@RequestBody InscrFoie inscrFoie) {
        return inscrFoieService.ajoutInscrFoie(inscrFoie);
    }

    @GetMapping
    public List<InscrFoie> getAllInscrFoie() {
        return inscrFoieService.getAllInscrFoie();
    }

    @DeleteMapping("/suppression/{idInscrFoie}")
    public ResponseEntity<String> supprimerInscrSang(@PathVariable int idInscrFoie) {
        inscrFoieService.supprimerInscrFoieId(idInscrFoie);
        return ResponseEntity.ok("Post supprimé avec succès !");
    }

    @PutMapping("modifier/{idInscrFoie}")
    public ResponseEntity<InscrFoie> modifierInscrFoie(@PathVariable int idInscrFoie, @RequestParam(required = false) Etat etat, @RequestParam(required = false)LocalDate date) {

        InscrFoie updateInscr = inscrFoieService.modifierEtatInscrFoie(idInscrFoie,etat,date);
        return ResponseEntity.ok(updateInscr);
    }
}

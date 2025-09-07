package org.example.corthreads.controllers;

import org.example.corthreads.models.Etat;
import org.example.corthreads.models.InscrSang;
import org.example.corthreads.services.InscrSangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incrsang")
public class InscrSangController {

    private final InscrSangService inscrSangService;

    public InscrSangController(InscrSangService inscrSangService) {
        this.inscrSangService = inscrSangService;
    }

    @PostMapping("ajout")
    public InscrSang ajouterInscrSang(@RequestBody InscrSang inscrSang) {
        return inscrSangService.ajoutInscrSang(inscrSang);
    }

    @GetMapping
    public List<InscrSang> getAllInscrSang() {
        return inscrSangService.getAllInscrSang();
    }

    @DeleteMapping("/suppression/{idInscrSang}")
    public ResponseEntity<String> supprimerInscrSang(@PathVariable int idInscrSang) {
        inscrSangService.supprimerInscrSangId(idInscrSang);
        return ResponseEntity.ok("Post supprimé avec succès !");
    }

    @PutMapping("modifier/{idInscrSang}")
    public ResponseEntity<InscrSang> modifierInscrSang(@PathVariable int idInscrSang, @RequestParam Etat etat) {

        InscrSang updateInscr = inscrSangService.modifierEtatInscrSang(idInscrSang, etat);
        return ResponseEntity.ok(updateInscr);
    }
}

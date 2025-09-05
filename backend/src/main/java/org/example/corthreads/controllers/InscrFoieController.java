package org.example.corthreads.controllers;

import org.example.corthreads.models.InscrFoie;
import org.example.corthreads.services.InscrFoieService;
import org.springframework.web.bind.annotation.*;

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
}

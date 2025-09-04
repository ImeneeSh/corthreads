package org.example.corthreads.controllers;

import org.example.corthreads.models.Reponse;
import org.example.corthreads.services.ReponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {

    private final ReponseService reponseService;

    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }

    @PostMapping("ajout")
    public Reponse ajouterReponse(@RequestBody Reponse reponse) {
        return reponseService.ajoutReponse(reponse);
    }

    @GetMapping("/{idUser}")
    public List<Reponse> getReponsesUtilisateur(@PathVariable String idUser) {
        return reponseService.getReponsesUtilisateur(idUser);
    }

    @GetMapping("/{idUser}/question")
    public List<Reponse> getReponsesQuestion(@PathVariable String idUser, @RequestParam String type, @RequestParam int idQst) {
        return reponseService.getReponsesParQst(idUser, type, idQst) ;
    }
}

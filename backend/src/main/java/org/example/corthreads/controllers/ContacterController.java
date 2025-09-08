package org.example.corthreads.controllers;

import org.example.corthreads.models.Contacter;
import org.example.corthreads.services.ContacterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacter")
public class ContacterController {

    private final ContacterService  contacterService;

    public ContacterController(ContacterService contacterService) {
        this.contacterService = contacterService;
    }

    @PostMapping("ajout")
    public Contacter ajouterContacter(@RequestBody Contacter contacter){
        return contacterService.ajouterContacter(contacter);
    }
}

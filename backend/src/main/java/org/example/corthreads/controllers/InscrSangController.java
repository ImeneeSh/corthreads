package org.example.corthreads.controllers;

import org.example.corthreads.models.InscrSang;
import org.example.corthreads.services.InscrSangService;
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
}

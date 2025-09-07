package org.example.corthreads.controllers;

import org.example.corthreads.models.ListeSang;
import org.example.corthreads.services.ListeSangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listesang")
public class ListeSangController {

    private final ListeSangService listeSangService ;

    public ListeSangController(ListeSangService listeSangService) {
        this.listeSangService = listeSangService;
    }

    @PostMapping("ajout")
    public ListeSang ajouterListeSang(@RequestBody ListeSang ListeSang) {
        return listeSangService.ajoutListeSang(ListeSang);
    }

    @GetMapping
    public List<ListeSang> getAllListeSang() {
        return listeSangService.getAllListeSang() ;
    }

    @DeleteMapping("/suppression/{idListeSang}")
    public ResponseEntity<String> supprimerListeSang(@PathVariable int idListeSang) {
        listeSangService.supprimerListeSangId(idListeSang);
        return ResponseEntity.ok("Post supprimé avec succès !");
    }
}

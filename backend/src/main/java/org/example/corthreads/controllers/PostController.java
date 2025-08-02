package org.example.corthreads.controllers;

import org.example.corthreads.models.*;
import org.example.corthreads.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/ajout")
    public Post ajouterPost(@RequestBody Post post) {
        return postService.ajouterPost(post);
    }

    @GetMapping("/recherche")
    public List<Post> rechercher(
            @RequestParam(required = false) Utilisateur utilisateurCritere,
            @RequestParam(required = false) TypePost typePost,
            @RequestParam(required = false) TypeDon typeDon,
            @RequestParam(required = false) GroupeSang groupSang,
            @RequestParam(required = false) Urgence urgence,
            @RequestParam(required = false) String Wilaya,
            @RequestParam(required = false) LocalDate datePublication) {

        return postService.rechercherPost(utilisateurCritere,typePost,typeDon,groupSang,urgence,Wilaya,datePublication);
    }
}

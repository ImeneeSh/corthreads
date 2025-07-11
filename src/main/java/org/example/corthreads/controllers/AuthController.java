package org.example.corthreads.controllers;

import org.example.corthreads.dto.AuthRequete ;
import org.example.corthreads.dto.AuthReponse ;
import org.example.corthreads.services.AuthService ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService ;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/connexion")
    public ResponseEntity<AuthReponse> connexion(@RequestBody AuthRequete rqt){
        AuthReponse rp =authService.authentifier(rqt.idUser(),rqt.mdp());
        return ResponseEntity.ok(rp);
    }
}

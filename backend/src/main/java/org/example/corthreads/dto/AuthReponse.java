package org.example.corthreads.dto;

import org.example.corthreads.models.Role;

public record AuthReponse (
        String idUser,
        String nom ,
        String prenom,
        Role role
){}

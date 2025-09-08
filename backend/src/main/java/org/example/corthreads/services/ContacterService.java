package org.example.corthreads.services;

import org.example.corthreads.models.Contacter;
import org.example.corthreads.repositories.ContacterRepository;
import org.springframework.stereotype.Service;

@Service
public class ContacterService {

    private final ContacterRepository contacterRepository;

    public ContacterService(ContacterRepository contacterRepository) {
        this.contacterRepository = contacterRepository;
    }

    public Contacter ajouterContacter(Contacter contacter) {
        return contacterRepository.save(contacter);
    }
}

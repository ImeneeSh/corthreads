package org.example.corthreads.services;

import org.example.corthreads.models.InscrSang;
import org.example.corthreads.repositories.InscrSangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscrSangService {

    private final InscrSangRepository inscrSangRepository;

    public InscrSangService(InscrSangRepository repo) {
        this.inscrSangRepository = repo;
    }

    public List<InscrSang> getAllInscrSang() {
        return inscrSangRepository.findAll();
    }

    public InscrSang ajoutInscrSang(InscrSang inscrSang) {
        return inscrSangRepository.save(inscrSang);
    }

}

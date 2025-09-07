package org.example.corthreads.services;

import org.example.corthreads.models.ListeSang;
import org.example.corthreads.repositories.ListeSangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeSangService {

    private final ListeSangRepository listeSangRepository;

    public ListeSangService(ListeSangRepository listeSangRepository) {
        this.listeSangRepository = listeSangRepository;
    }

    public List<ListeSang> getAllListeSang() {
        return listeSangRepository.findAll() ;
    }

    public ListeSang ajoutListeSang(ListeSang listeSang) {
        return listeSangRepository.save(listeSang);
    }

    public void supprimerListeSangId(int idListeSang) {
        if(!listeSangRepository.existsById(idListeSang)){
            throw new IllegalArgumentException("Ce post de sang n'existe pas");
        }
        listeSangRepository.deleteById(idListeSang);
    }
}

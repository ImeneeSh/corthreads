package org.example.corthreads.repositories;

import dtos.CritereRechercheValidation;
import dtos.ValidationDetails;
import models.Validation;

import java.util.List;

public interface ValidationRepo {

    Validation findValidation(String idVal);
    void saveValidation(Validation validation);
    void suppValidation(String idVal);

    ValidationDetails findValidationDetails(String idVal);

    List<Validation> findAllValidation(int page, int pageSize);
    List<Validation> findAllValidation();

    List<Validation> rechercherValidation(CritereRechercheValidation critere);
    List<Validation> rechercherValidation(CritereRechercheValidation critere, int page, int pageSize);
}
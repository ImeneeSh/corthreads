package org.example.corthreads.services;

import org.example.corthreads.models.QstAntc;
import org.example.corthreads.repositories.QstAntcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QstAntcService {

    private final QstAntcRepository qstAntcRepository;

    public QstAntcService(QstAntcRepository qstAntcRepository) {
        this.qstAntcRepository = qstAntcRepository;
    }

    public List<QstAntc> getAllQuestions() {
        return qstAntcRepository.findAll() ;
    }
}

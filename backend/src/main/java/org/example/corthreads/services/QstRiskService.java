package org.example.corthreads.services;

import org.example.corthreads.models.QstRisk;
import org.example.corthreads.repositories.QstRiskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QstRiskService {

    private final QstRiskRepository qstRiskRepository;

    public QstRiskService(QstRiskRepository qstRiskRepository) {
        this.qstRiskRepository = qstRiskRepository;
    }

    public List<QstRisk> getAllQuestions() {
        return qstRiskRepository.findAll() ;
    }
}

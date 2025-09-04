package org.example.corthreads.services;

import org.example.corthreads.models.QstGen;
import org.example.corthreads.repositories.QstGenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QstGenService {

    private final QstGenRepository qstGenRepository;

    public QstGenService(QstGenRepository qstGenRepository) {
        this.qstGenRepository = qstGenRepository;
    }

    public List<QstGen> getAllQuestions() {
        return qstGenRepository.findAll() ;
    }
}

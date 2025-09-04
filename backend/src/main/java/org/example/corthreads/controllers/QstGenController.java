package org.example.corthreads.controllers;

import org.example.corthreads.models.QstGen;
import org.example.corthreads.services.QstGenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qstgen")
public class QstGenController {

    private final QstGenService qstGenService;

    public QstGenController(QstGenService qstGenService) {
        this.qstGenService = qstGenService;
    }

    @GetMapping
    public List<QstGen> getAllQuestions() {
        return qstGenService.getAllQuestions() ;
    }
}

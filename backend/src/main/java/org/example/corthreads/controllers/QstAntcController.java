package org.example.corthreads.controllers;

import org.example.corthreads.models.QstAntc;
import org.example.corthreads.services.QstAntcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qstantc")
public class QstAntcController {

    private final QstAntcService qstAntcService;

    public QstAntcController(QstAntcService qstAntcService) {
        this.qstAntcService = qstAntcService;
    }

    @GetMapping
    public List<QstAntc> getAllQuestions() {
        return qstAntcService.getAllQuestions() ;
    }
}

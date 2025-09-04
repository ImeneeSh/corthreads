package org.example.corthreads.controllers;

import org.example.corthreads.models.QstRisk;
import org.example.corthreads.services.QstRiskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qstrisk")
public class QstRiskController {

    private final QstRiskService qstRiskService;

    public QstRiskController(QstRiskService qstRiskService) {
        this.qstRiskService = qstRiskService;
    }

    @GetMapping
    public List<QstRisk> getAllQuestions() {
        return qstRiskService.getAllQuestions() ;
    }
}

package com.example.demo.controller;

import com.example.demo.model.Candidate;
import com.example.demo.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = candidateService.findAll();
        return candidates;
    }

}

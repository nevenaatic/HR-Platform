package com.example.demo.service;


import com.example.demo.model.Candidate;

import java.util.List;

public interface CandidateService {
    Candidate save(Candidate candidate);
    void delete(long id);
    Candidate findById(long id);
    boolean existByEmail(String email);
    List<Candidate> findAll();
    List<Candidate> findBySkillName(String skillName);
    List<Candidate> findBySkillsName(List<String> skills);
}

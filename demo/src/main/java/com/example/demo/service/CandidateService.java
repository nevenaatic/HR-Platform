package com.example.demo.service;


import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;

import java.util.List;

public interface CandidateService {
    Candidate save(Candidate candidate);
    void delete(long id);
    Candidate findById(long id);
    Candidate findCandidateWithSkills(long id);
    List<Candidate> findAll();
    List<Candidate> findBySkillName(String skillName);
    List<Candidate> findBySkillsName(List<String> skills);
    List<Candidate> findByName(String name);
    Candidate findByEmail(String email);
    Candidate deleteSkillForCandidate(long candidateId, Skill skill);
    Candidate addSkillToCandidate(long candidateId, Skill skill);
    Candidate updateCandidate(Candidate candidate);

}

package com.example.demo.DAO;

import com.example.demo.model.Candidate;
import lombok.AllArgsConstructor;

import java.util.List;

public interface CandidateDao {
    Candidate addCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(long id);
    Candidate getCandidateById(long id);
    List<Candidate> getAllCandidates();
    List<Candidate> getCandidatesBySkill(String skillName);
//    List<Candidate> getCandidatesBySkills(List<String> skillName);
    boolean existByEmail(String email);
}

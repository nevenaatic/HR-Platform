package com.example.demo.DAO;

import com.example.demo.model.Candidate;
import com.example.demo.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class CandidateDaoImplementation implements CandidateDao{
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
       return candidateRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate getCandidateById(long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public List<Candidate> getCandidatesBySkill(String skillName) {
        return null;
    }

//    @Override
//    public List<Candidate> getCandidatesBySkills(List<String> skillNames) {
//        return candidateRepository.findBySkillsName(skillNames);
//    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }
}

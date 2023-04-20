package com.example.demo.service;
import com.example.demo.model.Candidate;
import com.example.demo.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate save(Candidate candidate) {
        if(findByEmail(candidate.getEmail())!=null){
            throw new EntityExistsException("Candidate with this email already exist");
        }
        return candidateRepository.save(candidate);
    }

    @Override
    public void delete(long id) {
        if(candidateRepository.findById(id) == null){
            throw new EntityExistsException("Candidate doesn't exist!");
        }
        candidateRepository.deleteById(id);

    }

    @Override
    public Candidate findById(long id) {
       Candidate candidate = candidateRepository.findById(id);
       if(candidate == null){
          throw new EntityNotFoundException("Candidate doesn't exist");
       }
       return candidate;
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public List<Candidate> findBySkillName(String skillName) {
        return candidateRepository.findBySkillName(skillName);
    }

    @Override
    public List<Candidate> findBySkillsName(List<String> skills) {
        return candidateRepository.findBySkillsName(skills);
    }

    @Override
    public List<Candidate> findByName(String name) {
        return candidateRepository.findByName(name);
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
}

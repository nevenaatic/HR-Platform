package com.example.demo.service;

import com.example.demo.DAO.CandidateDao;
import com.example.demo.model.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateDao candidateDao;
    @Autowired
    public CandidateServiceImpl(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }
    @Override
    public Candidate save(Candidate candidate) {
        if(candidateDao.existByEmail(candidate.getEmail())){
            throw new EntityExistsException("Candidate with this email already exist");
        }
        return candidateDao.addCandidate(candidate);
    }

    @Override
    public void delete(long id) {
        if(candidateDao.getCandidateById(id) == null){
            throw new EntityExistsException("Candidate doesn't exist!");
        }
            candidateDao.deleteCandidate(id);

    }

    @Override
    public Candidate findById(long id) {
       Candidate candidate = candidateDao.getCandidateById(id);
       if(candidate == null){
          throw new EntityNotFoundException("Candidate doesn't exist");
       }
       return candidate;
    }

    @Override
    public boolean existByEmail(String email) {
        return candidateDao.existByEmail(email);
    }

    @Override
    public List<Candidate> findAll() {
        return candidateDao.getAllCandidates();
    }

    @Override
    public List<Candidate> findBySkillName(String skillName) {
        return null;
    }

    @Override
    public List<Candidate> findBySkillsName(List<String> skills) {
        return null;
    }
}

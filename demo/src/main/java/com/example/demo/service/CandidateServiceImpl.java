package com.example.demo.service;
import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final SkillRepository skillRepository;
    @Override
    public Candidate save(Candidate candidate) {
        if(findByEmail(candidate.getEmail())!=null)
            throw new EntityExistsException("Candidate with this email already exist");
        candidate.setSkillList(checkSkills(candidate.getSkillList()));

        return candidateRepository.save(candidate);
    }

    @Override
    public void delete(long id) {
        Candidate candidate = candidateRepository.findWithSkills(id);
        if(candidate == null)
            throw new EntityExistsException("Candidate doesn't exist!");
        candidate.getSkillList().forEach(skill -> skill.getCandidates().remove(candidate));
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
    public Candidate findCandidateWithSkills(long id) {
        return candidateRepository.findWithSkills(id);
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
        List<String> skillLower = new ArrayList<>();
        if(skills.size()!= 0){
            for(String s : skills) {
                skillLower.add(s.toLowerCase());
            }
        }
        return candidateRepository.findBySkillsNameAND(skillLower, skillLower.size());
    }

    @Override
    public List<Candidate> findByName(String name) {
        return candidateRepository.findByName(name);
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }
    @Override
    public Candidate deleteSkillForCandidate(long candidateId, Skill skill) {
        Candidate candidate= candidateRepository.findWithSkills(candidateId);
        candidate.getSkillList().remove(skill);
       return candidateRepository.save(candidate);
    }

    public Candidate addSkillToCandidate(long candidateId, Skill skill) {
        Candidate candidate = findCandidateWithSkills(candidateId);
        candidate.getSkillList().add(skill);
       return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Candidate candidate) {
        Candidate candidateForUpdate = findCandidateWithSkills(candidate.getId());
        candidateForUpdate.setFullName(candidate.getFullName());
        if(!candidate.getEmail().equals(candidateForUpdate.getEmail()) && candidateRepository.findByEmail(candidate.getEmail())!=null)
            throw new EntityExistsException("This email is already taken!");

        candidateForUpdate.setEmail(candidate.getEmail());
        candidateForUpdate.setContactNumber(candidate.getContactNumber());
        candidateForUpdate.setDateOfBirth(candidate.getDateOfBirth());
        candidateForUpdate.setSkillList(checkSkills(candidate.getSkillList()));
        return  candidateRepository.save(candidateForUpdate);
    }
    private Set<Skill> checkSkills(Set<Skill> candidateSkills){
        Set<Skill> checkedSkills = new HashSet<>();
        for (Skill s : candidateSkills){
            if(skillRepository.findByName(s.getName())!=null)
                checkedSkills.add(skillRepository.findByName(s.getName()));
            else
                checkedSkills.add(skillRepository.save(new Skill(0, s.getName(), null)));
        }
        return checkedSkills;
    }
}

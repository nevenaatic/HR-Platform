package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService{

    private final SkillRepository skillRepository;
    private final CandidateService candidateService;

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void deleteById(long id) {
        Skill skill = skillRepository.findSkillWithCandidates(id);
        skill.getCandidates().forEach(candidate -> candidate.getSkillList().remove(skill));
        skillRepository.deleteById(id);
    }

    @Override
    public void deleteSkillForCandidate(long id, long candidateId) {
        candidateService.deleteSkillForCandidate(candidateId, findById(id));
    }

    @Override
    public Skill findById(long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill updateSKill(Skill skill) {
        Skill skillForUpdate = findById(skill.getId());
        if(findByName(skill.getName()) != null)
            throw new EntityExistsException("This skill name is taken!");
        skillForUpdate.setName(skill.getName());
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findByName(String name) {
        return skillRepository.findByName(name);
    }

    public Skill findOrCreateSkill(String name){
        Skill skill = skillRepository.findByName(name);
        if(skill != null){
            return skill;
        }
        return skillRepository.save(new Skill(0, name,null));
    }
}

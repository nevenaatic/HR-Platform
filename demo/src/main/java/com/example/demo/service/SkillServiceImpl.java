package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService{

    private final SkillRepository skillRepository;

    @Override
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void deleteById(long id) {
         skillRepository.deleteById(id);
    }

    @Override
    public Skill findById(long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill updateSKill(Skill skill) {
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
}

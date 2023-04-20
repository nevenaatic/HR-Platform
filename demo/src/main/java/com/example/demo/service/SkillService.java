package com.example.demo.service;

import com.example.demo.model.Skill;

import java.util.List;

public interface SkillService {
    Skill save(Skill skill);
    void deleteById(long id);
    Skill findById(long id);
    Skill updateSKill(Skill skill);
    List<Skill> getAll();
    Skill findByName(String name);
}

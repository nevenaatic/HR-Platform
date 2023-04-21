package com.example.demo.repository;

import com.example.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    Skill findById(long id);
    @Query("select s from Skill s where lower(s.name) like lower(?1)")
    Skill findByName(String name);
    @Query("select s from Skill s join s.candidates c where s.id =?1" )
    Skill findSkillWithCandidates(long id);
}

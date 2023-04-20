package com.example.demo.repository;

import com.example.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
//    @Query("SELECT c FROM Candidate c JOIN c.skills s WHERE s.name IN ?1")
//    List<Candidate> findBySkillsName(List<String> skillNames);

    @Query("SELECT c FROM Candidate c JOIN c.skillList s WHERE s.name = ?1")
    List<Candidate> findBySkillName(String skillName);

    Candidate findByEmail(String email);

}

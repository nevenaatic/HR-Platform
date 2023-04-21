package com.example.demo.repository;

import com.example.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("select c from Candidate c join c.skillList s where lower(s.name) IN ?1")
    List<Candidate> findBySkillsName(List<String> skillNames);
    @Query("select c from Candidate c join c.skillList s where lower(s.name) IN ?1 GROUP BY c HAVING COUNT(distinct s.id) = ?2 ")
    List<Candidate> findBySkillsNameAND(List<String> skillNames, long count);
    @Query("select c from Candidate c join c.skillList s where s.name = ?1")
    List<Candidate> findBySkillName(String skillName);
    @Query("select c from Candidate c where lower(c.fullName) like lower(concat('%', ?1, '%'))")
    List<Candidate> findByName(String name);
    Candidate findByEmail(String email);
    Candidate findById(long id);
    @Query("select c from Candidate c join c.skillList s WHERE c.id = ?1")
    Candidate findWithSkills(long id);

}

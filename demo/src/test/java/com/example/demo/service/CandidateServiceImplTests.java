package com.example.demo.service;

import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.SkillRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceImplTests {

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    CandidateServiceImpl candidateService;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
    }

    @Test
   public void save() {
        //given
        Candidate candidate = new Candidate(0L, "Pera Peric", new Date(),
                "1234567890", "pera@gmail.com", new HashSet<>());

        when(candidateRepository.findByEmail(any())).thenReturn(null);
        when(candidateRepository.save(any())).thenReturn(candidate);

        //when
        Candidate savedCandidate = candidateService.save(candidate);

        Assertions.assertNotNull(savedCandidate);
        Assertions.assertEquals(candidate.getFullName(), savedCandidate.getFullName());
        Assertions.assertEquals(candidate.getEmail(), savedCandidate.getEmail());
        Assertions.assertEquals(candidate.getContactNumber(), savedCandidate.getContactNumber());
        Assertions.assertEquals(candidate.getDateOfBirth(), savedCandidate.getDateOfBirth());
        Assertions.assertEquals(candidate.getSkillList(), savedCandidate.getSkillList());
    }

    @Test
    public void getAll() {
        Candidate candidate1 = new Candidate(0L, "Mara Maric", new Date(),
                "1234567890", "mara@gmail.com", new HashSet<>());
        Candidate candidate2 = new Candidate(0L, "Pera Peric", new Date(),
                "1234567890", "pera@gmail.com", new HashSet<>());

        List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate1);
        candidates.add(candidate2);
        doReturn(candidates).when(candidateRepository).findAll();
        List<Candidate> candidatesReturn = candidateService.findAll();
        Assertions.assertEquals(candidates, candidatesReturn);
    }

    @Test
    public void updateCandidate() {
        Candidate candidate = new Candidate(1L, "Mara Maric", new Date(),
                "1234567890", "mara@gmail.com", new HashSet<>());

        when(candidateService.findCandidateWithSkills(1L)).thenReturn(candidate);
        Candidate updatedCandidate = new Candidate(1L, "Mara Arsic", new Date(), "000000000", "maraarsic@gmail.com", new HashSet<>());
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(new Skill(1L, "Java", new HashSet<>()));
        candidateService.updateCandidate(updatedCandidate);
        Assert.assertEquals(candidate.getSkillList(), updatedCandidate.getSkillList());
        Assert.assertEquals(candidate.getFullName(), updatedCandidate.getFullName());
        Assert.assertEquals(candidate.getEmail(), updatedCandidate.getEmail());
        Assert.assertEquals(candidate.getContactNumber(), updatedCandidate.getContactNumber());
    }

    @Test
    public void addSkillToCandidate(){
        Candidate candidate = new Candidate(1L, "Mara Maric", new Date(),
                "1234567890", "mara@gmail.com", new HashSet<>());
        Skill skill = new Skill(1L, "Java", new HashSet<>());

        when(candidateService.findCandidateWithSkills(1L)).thenReturn(candidate);

        candidateService.addSkillToCandidate(1L,skill);
        Assert.assertEquals(1, candidate.getSkillList().size());
    }

}

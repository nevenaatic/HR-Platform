package com.example.demo.controller;

import com.example.demo.dto.SkillSearchDto;
import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;
import com.example.demo.service.CandidateService;
import com.example.demo.service.SkillService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidateSkillControllerTests {


    @Mock
    private CandidateService candidateService;

    @Mock
    private SkillService skillService;

    @InjectMocks
    private CandidateSkillController candidateSkillController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSkillToCandidate() {
        long id = 1L;
        String skillName = "ime";
        Candidate candidate = new Candidate(1L, "Kandidat 1", null, "1111", "k1@mejl", new HashSet<>());
        Skill skill = new Skill();
        skill.setName(skillName);
        when(candidateService.findById(id)).thenReturn(candidate);
        when(skillService.findOrCreateSkill(skillName)).thenReturn(skill);
        ResponseEntity<Candidate> response = candidateSkillController.addSkillToCandidate(id, skillName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateService).addSkillToCandidate(id, skill);
    }

    @Test
    public void testDeleteSkillFromCandidate() {
        long idCandidate = 1L;
        long skillId = 2L;
        Candidate candidate = new Candidate(1L, "Kandidat 1", null, "1111", "k1@mejl", new HashSet<>());
        Skill skill = new Skill();
        when(candidateService.findById(idCandidate)).thenReturn(candidate);
        when(skillService.findById(skillId)).thenReturn(skill);
        ResponseEntity<Candidate> response = candidateSkillController.deleteSkillFromCandidate(idCandidate, skillId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(candidateService).deleteSkillForCandidate(idCandidate, skill);
    }

    @Test
    public void testFindBySkills() {
        SkillSearchDto skillList = new SkillSearchDto();
        skillList.setSkillList(Arrays.asList("prva", "druga"));
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate());
        when(candidateService.findBySkillsName(skillList.getSkillList())).thenReturn(candidates);
        ResponseEntity<List<Candidate>> response = candidateSkillController.findBySkills(skillList);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidates, response.getBody());
    }
}

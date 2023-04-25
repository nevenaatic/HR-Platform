package com.example.demo.service;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTests {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Skill skill1 = new Skill(1L, "Java", new HashSet<>());
        Mockito.when(skillRepository.save(skill1)).thenReturn(skill1);

        Skill savedSkill = skillService.save(skill1);

        assertNotNull(savedSkill);
        assertEquals(skill1.getId(), savedSkill.getId());
        assertEquals(skill1.getName(), savedSkill.getName());
    }
    @Test
    public void testUpdateSkill() {
        Skill updatedSkill = new Skill(1L, "Java 2.0", null);
        Skill skill1 = new Skill(1L, "Java", new HashSet<>());
        Mockito.when(skillRepository.findById(Mockito.anyLong())).thenReturn(skill1);
        Mockito.when(skillRepository.findByName(Mockito.anyString())).thenReturn(null);
        Mockito.when(skillRepository.save(Mockito.any(Skill.class))).thenReturn(updatedSkill);

        Skill result = skillService.updateSKill(updatedSkill);

        assertNotNull(result);
        assertEquals(updatedSkill.getId(), result.getId());
        assertEquals(updatedSkill.getName(), result.getName());
    }
}

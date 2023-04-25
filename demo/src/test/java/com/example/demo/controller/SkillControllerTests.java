package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import com.example.demo.service.SkillServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillControllerTests {

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillController skillController;

    @Test
    public void testGetAllSkills() throws Exception {
        Skill skill1 = new Skill(1L, "Java", new HashSet<>());
        Skill skill2 = new Skill(2L, "Python", new HashSet<>());
        List<Skill> skills = Arrays.asList(skill1, skill2);
        when(skillService.getAll()).thenReturn(skills);
        ResponseEntity<List<Skill>> response = skillController.getAllSkills();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(skills, response.getBody());
    }
    @Test
    public void testGetById() {
        Skill skill1 = new Skill(1L, "Java", new HashSet<>());
        when(skillService.findById(1)).thenReturn(skill1);

        ResponseEntity<Skill> response = skillController.getById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(skill1, response.getBody());
    }
    @Test
    public void testGetByIdNotFound() {
        when(skillService.findById(1)).thenReturn(null);

        ResponseEntity<Skill> response = skillController.getById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddSkillAlreadyExists() {
        Skill existingSkill = new Skill(1, "Java", null);
        when(skillService.findByName("Java")).thenReturn(existingSkill);

        ResponseEntity<Skill> response = skillController.addSkill(existingSkill);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}

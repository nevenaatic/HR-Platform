package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.SkillService;
import com.example.demo.service.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

public class SkillControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SkillController skillController = new SkillController(skillService);
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
    }

    @Test
    public void testGetAllSkills() throws Exception {
        Skill skill1 = new Skill(1L, "Java", new HashSet<>());
        Skill skill2 = new Skill(2L, "Python", new HashSet<>());
        List<Skill> skills = Arrays.asList(skill1, skill2);
        when(skillService.getAll()).thenReturn(skills);
        mockMvc.perform(MockMvcRequestBuilders.get("/skills"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{'id': 1, 'name': 'Java'},{'id': 2, 'name': 'Python'}]"));
    }

    @Test
    public void testAddSkillBadRequest() throws Exception {
        when(skillService.findByName("Java")).thenReturn(new Skill(1L, "Java", new HashSet<>()));
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                        .content("{\"name\": \"Java\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

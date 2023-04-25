package com.example.demo.controller;

import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.CandidateService;
import com.example.demo.service.CandidateServiceImpl;
import com.example.demo.service.SkillService;
import com.example.demo.service.SkillServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CandidateControllerTests {
    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        CandidateController candidateController = new CandidateController(candidateService);
        mvc = MockMvcBuilders.standaloneSetup(candidateController).build();
    }

    @Test
    public void testGetAllCandidates() throws Exception {
        Candidate c1 = new Candidate(1L, "Kandidat 1", null, "1111", "k1@mejl", new HashSet<>());
        Candidate c2 = new Candidate(2L, "Kandidat 2", null, "1111", "k2@mejl", new HashSet<>());
        List<Candidate> expectedCandidates = Arrays.asList(c1, c2);
        when(candidateRepository.findAll()).thenReturn(expectedCandidates);

        mvc.perform(MockMvcRequestBuilders.get("/candidates"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{'id':1, 'fullName': 'Kandidat 1', 'dateOfBirth':null, 'email':'k1@mejl', 'skillList':[]}, {'id':2, 'fullName': 'Kandidat 2', 'dateOfBirth':null, 'email':'k2@mejl', 'skillList':[]}]"));

    }

    @Test
    public void testCreateCandidate() throws Exception {
        Candidate c1 = new Candidate(1L, "Kandidat 1", null, "1111", "k1@mejl", new HashSet<>());
        ObjectMapper mapper = new ObjectMapper();
        String candidateJson = mapper.writeValueAsString(c1);

        when(candidateService.save(c1)).thenReturn(c1);

        mvc.perform(MockMvcRequestBuilders.post("/candidates/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(candidateJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
                //.andExpect(MockMvcResultMatchers.content().json("{'id':1, 'fullName': 'Kandidat 1', 'dateOfBirth':null, 'email':'k1@mejl', 'skillList':[]}"));
    }
}

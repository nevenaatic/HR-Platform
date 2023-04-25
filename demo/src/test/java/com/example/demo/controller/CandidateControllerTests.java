package com.example.demo.controller;

import com.example.demo.model.Candidate;
import com.example.demo.service.CandidateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CandidateControllerTests {
    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(1L, "Mara MAric", null, "11111", "mara@gmail.com",  new HashSet<>()));
        candidates.add(new Candidate(2L, "Mira Miric",null, "11111", "mirkaa@gmail.com",  new HashSet<>()));

        when(candidateService.findAll()).thenReturn(candidates);

        ResponseEntity<List<Candidate>> response = candidateController.getAllCandidates();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidates, response.getBody());
    }


    @Test
    public void getById() {
        long id = 1L;
        Candidate candidate = new Candidate(1L, "Mara MAric", null, "11111", "mara@gmail.com",  new HashSet<>());

        when(candidateService.findById(id)).thenReturn(candidate);

        ResponseEntity<Candidate> response = candidateController.getById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidate, response.getBody());
    }

    @Test
    public void getByIdNotFound() {
        long id = 1L;

        when(candidateService.findById(id)).thenReturn(null);

        ResponseEntity<Candidate> response = candidateController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void updateCandidate() {
        long id = 1L;
        Candidate candidate = new Candidate(1L, "Mara MAric", null, "11111", "mara@gmail.com",  new HashSet<>());

        when(candidateService.findById(id)).thenReturn(candidate);
        when(candidateService.updateCandidate(candidate)).thenReturn(candidate);

        ResponseEntity<Candidate> response = candidateController.updateCandidate(id, candidate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(candidate, response.getBody());
    }

    @Test
    public void updateCandidateNotFound() {
        long id = 1L;
        Candidate candidate = new Candidate(1L, "Mara MAric", null, "11111", "mara@gmail.com",  new HashSet<>());

        when(candidateService.findById(id)).thenReturn(null);

        ResponseEntity<Candidate> response = candidateController.updateCandidate(id, candidate);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testFindByName() {
        Candidate candidate1 = new Candidate(1l, "Ana Anic", null, "11111", "aaaa", new HashSet<>());
        Candidate candidate2 = new Candidate(1l, "Ana Minic", null, "11111", "aaaa2", new HashSet<>());
         List<Candidate> candidates = new ArrayList<>();
        candidates.add(candidate1);
         candidates.add(candidate2);

        when(candidateService.findByName("ana")).thenReturn(candidates);
        ResponseEntity<List<Candidate>> response = candidateController.findByName("ana");

        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(candidate1));
        assertTrue(response.getBody().contains(candidate2));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByNameWithNoMatch() {
        String name = "Joca";
        when(candidateService.findByName(name)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Candidate>> response = candidateController.findByName(name);

        verify(candidateService).findByName(name);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}

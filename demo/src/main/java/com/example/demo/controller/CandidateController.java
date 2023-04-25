package com.example.demo.controller;

import com.example.demo.dto.SkillSearchDto;
import com.example.demo.model.Candidate;
import com.example.demo.service.CandidateService;
import com.example.demo.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.findAll();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable long id) {
        Candidate candidate = candidateService.findById(id);
        if(candidate == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate) {
      return new ResponseEntity<>(candidateService.save(candidate), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable long id, @RequestBody Candidate candidate) {
      if(candidateService.findById(id) == null)
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(candidateService.updateCandidate(candidate), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCandidate(@PathVariable long id) {
        if(candidateService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        candidateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/candidate/{name}")
    public ResponseEntity<List<Candidate>> findByName(@PathVariable String name) {
        List<Candidate> candidates = candidateService.findByName(name);
        if(candidates.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

}

package com.example.demo.controller;

import com.example.demo.dto.SkillSearchDto;
import com.example.demo.model.Candidate;
import com.example.demo.model.Skill;
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
    private final SkillService skillService;

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
    @PostMapping("/{id}")
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

//    @GetMapping("/candidate/skill/{skill}")
//    public ResponseEntity<List<Candidate>> findBySkill(@PathVariable String skill) {
//        List<Candidate> candidates = candidateService.findBySkillName(skill);
//        if(candidates.size() == 0)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(candidates, HttpStatus.OK);
//    }

    @PostMapping("/search")
    public ResponseEntity<List<Candidate>> findBySkills(@RequestBody SkillSearchDto skillList) {
        List<Candidate> candidates = candidateService.findBySkillsName(skillList.getSkillList());
        if(candidates.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("/{id}/skill")
    public ResponseEntity<Candidate> addSkillToCandidate(@PathVariable long id, @RequestBody String skillName){
        if(candidateService.findById(id)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(candidateService.addSkillToCandidate(id,skillService.findOrCreateSkill(skillName) ), HttpStatus.OK) ;
    }

//    @PostMapping("/candidate/")
//    public ResponseEntity<List<Candidate>> searchCandidateByName(@RequestBody String name){
//        return new ResponseEntity<>(candidateService.findByName(name), HttpStatus.OK) ;
//    }
//    @PostMapping("/candidate/skills")
//    public ResponseEntity<List<Candidate>> searchCandidateBySkill(@RequestBody List<String> skillList){
//        return new ResponseEntity<>(candidateService.findBySkillsName(skillList), HttpStatus.OK) ;
//    }
}

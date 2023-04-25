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
@RequestMapping("/candidate-skill")
@AllArgsConstructor
public class CandidateSkillController {
    private final CandidateService candidateService;
    private final SkillService skillService;

    @PostMapping("/{id}")
    public ResponseEntity<Candidate> addSkillToCandidate(@PathVariable long id, @RequestBody String skillName){
        if(candidateService.findById(id)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(candidateService.addSkillToCandidate(id,skillService.findOrCreateSkill(skillName) ), HttpStatus.OK) ;
    }

    @DeleteMapping("/{idCandidate}/skill/{skillId}")
    public ResponseEntity<Candidate> deleteSkillFromCandidate(@PathVariable long idCandidate, @PathVariable long skillId){
        if(candidateService.findById(idCandidate)==null || skillService.findById(skillId)==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(candidateService.deleteSkillForCandidate(idCandidate,skillService.findById(skillId) ), HttpStatus.OK) ;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Candidate>> findBySkills(@RequestBody SkillSearchDto skillList) {
        List<Candidate> candidates = candidateService.findBySkillsName(skillList.getSkillList());
        if(candidates.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }
}

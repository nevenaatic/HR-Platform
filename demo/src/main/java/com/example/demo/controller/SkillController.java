package com.example.demo.controller;

import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@AllArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills(){
        List<Skill> skills = skillService.getAll();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable long id){
        Skill skill = skillService.findById(id);
        if(skill==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill){

        if(skillService.findByName(skill.getName()) != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Skill createdSkill = skillService.save(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable long id, @RequestBody Skill skill){

        if(skillService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if(skillService.findByName(skill.getName()) != null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill createdSkill = skillService.updateSKill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable long id){

        if(skillService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       skillService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

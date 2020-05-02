package com.daria.demospring.rest;

import com.daria.demospring.dto.SkillDto;
import com.daria.demospring.model.Skill;
import com.daria.demospring.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/skill/")
public class SkillRestControllerV1 {
    private final SkillService skillService;

    @Autowired
    public SkillRestControllerV1(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SkillDto> getSkill(@PathVariable("id") Long skillId) {
        Skill skill = skillService.findById(skillId);

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SkillDto skillDto = SkillDto.fromSkill(skill);

        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }

    @PostMapping(value = "/saveSkill")
    public ResponseEntity<Skill> saveSkill(@RequestBody @Valid Skill skill) {
        HttpHeaders headers = new HttpHeaders();

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.skillService.register(skill);
        return new ResponseEntity<>(skill, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateSkill")
    public ResponseEntity<Skill> updateSkill(@RequestBody @Valid Skill skill) {
        HttpHeaders headers = new HttpHeaders();

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.skillService.register(skill);

        return new ResponseEntity<>(skill, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteSkill/{id}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable("id") Long id) {
        Skill skill = this.skillService.findById(id);

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.skillService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/allSkill")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = this.skillService.getAll();

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(skills, HttpStatus.OK);
    }
}

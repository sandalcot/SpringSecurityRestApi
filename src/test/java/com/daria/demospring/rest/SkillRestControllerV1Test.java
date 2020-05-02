package com.daria.demospring.rest;

import com.daria.demospring.dto.SkillDto;
import com.daria.demospring.model.Skill;
import com.daria.demospring.service.SkillService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SkillRestControllerV1Test {

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillRestControllerV1 skillRestControllerV1;

    private Skill skill = new Skill();

    @Test
    void getSkill() {
        skill.setId(1l);
        when(skillService.findById(skill.getId())).thenReturn(skill);
        ResponseEntity<SkillDto> responseEntity = skillRestControllerV1.getSkill(skill.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void saveSkill() {
        ResponseEntity responseEntity = skillRestControllerV1.saveSkill(skill);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateSkill() {
        ResponseEntity responseEntity = skillRestControllerV1.updateSkill(skill);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteSkill() {
        when(skillService.findById(skill.getId())).thenReturn(skill);
        ResponseEntity responseEntity = skillRestControllerV1.deleteSkill(skill.getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill());
        when(skillService.getAll()).thenReturn(skills);
        ResponseEntity<List<Skill>> response = skillRestControllerV1.getAllSkills();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
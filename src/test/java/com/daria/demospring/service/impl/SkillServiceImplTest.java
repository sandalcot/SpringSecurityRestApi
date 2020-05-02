package com.daria.demospring.service.impl;

import com.daria.demospring.model.Skill;
import com.daria.demospring.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    private Skill skill = new Skill();

    @Test
    void register() {
        skillService.register(skill);
        verify(skillRepository).save(skill);
    }

    @Test
    void findById() {
        skill.setId(1l);
        skillService.findById(1l);
        verify(skillRepository).findById(skill.getId());
    }

    @Test
    void delete() {
        skill.setId(1l);
        skillService.delete(1l);
        verify(skillRepository).deleteById(skill.getId());
    }

    @Test
    void getAll() {
        List<Skill> skills = new ArrayList<>();
        when(skillRepository.findAll()).thenReturn(skills);
        skillService.getAll();
        verify(skillRepository).findAll();
    }
}
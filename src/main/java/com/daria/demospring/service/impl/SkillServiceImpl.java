package com.daria.demospring.service.impl;

import com.daria.demospring.model.Skill;
import com.daria.demospring.repository.SkillRepository;
import com.daria.demospring.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill register(Skill skill) {
        skillRepository.save(skill);
        skillRepository.flush();
        log.info("IN SkillServiceImpl save {}", skill);
        return skill;
    }

    @Override
    public Skill findByName(String name) {
        Skill result = skillRepository.findByName(name);
        log.info("IN findByName - skill: {} found by username: {}", result, name);
        return result;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = skillRepository.findAll();
        log.info("IN getAll - {} skills found", result.size());
        return result;
    }

    @Override
    public Skill findById(Long id) {
        Skill result = skillRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no skill found by id: {}", id);
            return null;
        }

        log.info("IN findById - skill: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        log.info("IN SkillServiceImpl delete {}", id);
        skillRepository.deleteById(id);
    }
}

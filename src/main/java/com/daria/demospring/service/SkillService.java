package com.daria.demospring.service;

import com.daria.demospring.model.Skill;

import java.util.List;

public interface SkillService {

    Skill register(Skill skill);

    Skill findByName(String name);

    List<Skill> getAll();

    Skill findById(Long id);

    void delete(Long id);
}

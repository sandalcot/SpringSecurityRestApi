package com.daria.demospring.dto;

import com.daria.demospring.model.Developer;
import com.daria.demospring.model.Skill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillDto {
    private Long id;
    private String name;
    private List<Developer> developers;

    public static SkillDto fromSkill(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skill.setId(skill.getId());
        skillDto.setName(skill.getName());
        skillDto.setDevelopers(skillDto.getDevelopers());
        return skillDto;
    }
}

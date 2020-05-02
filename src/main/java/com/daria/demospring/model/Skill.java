package com.daria.demospring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "skill")
@Data
public class Skill extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    private Set<Developer> developer;
}

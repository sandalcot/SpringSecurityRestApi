package com.daria.demospring.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "developer")
@Data
public class Developer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developer_skill",
            joinColumns = {@JoinColumn(name = "developer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id", referencedColumnName = "id")})
    private Set<Skill> skill;

    @OneToOne()
    @JoinColumn(name = "account_id")
    private Account account;
}

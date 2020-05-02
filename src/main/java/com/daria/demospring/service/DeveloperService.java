package com.daria.demospring.service;

import com.daria.demospring.model.Developer;

import java.util.List;

public interface DeveloperService {

    Developer register(Developer developer);

    List<Developer> getAll();

    Developer findById(Long id);

    void delete(Long id);
}

package com.daria.demospring.service.impl;

import com.daria.demospring.model.Developer;
import com.daria.demospring.repository.DeveloperRepository;
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
class DeveloperServiceImplTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperServiceImpl developerService;

    private Developer developer = new Developer();

    @Test
    void register() {
        developerService.register(developer);
        verify(developerRepository).save(developer);
    }

    @Test
    void findById() {
        developer.setId(1l);
        developerService.findById(1l);
        verify(developerRepository).findById(developer.getId());
    }

    @Test
    void delete() {
        developer.setId(1l);
        developerService.delete(1l);
        verify(developerRepository).deleteById(developer.getId());
    }

    @Test
    void getAll() {
        List<Developer> developers = new ArrayList<>();
        when(developerRepository.findAll()).thenReturn(developers);
        developerService.getAll();
        verify(developerRepository).findAll();
    }
}
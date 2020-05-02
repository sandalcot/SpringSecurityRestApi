package com.daria.demospring.rest;

import com.daria.demospring.dto.DeveloperDto;
import com.daria.demospring.model.Developer;
import com.daria.demospring.service.DeveloperService;
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
class DeveloperControllerV1Test {

    @Mock
    private DeveloperService developerService;

    @InjectMocks
    private DeveloperControllerV1 developerControllerV1;

    private Developer developer = new Developer();

    @Test
    void getDeveloper() {
        when(developerService.findById(developer.getId())).thenReturn(developer);
        ResponseEntity<DeveloperDto> responseEntity = developerControllerV1.getDeveloper(developer.getId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void saveDeveloper() {
        ResponseEntity responseEntity = developerControllerV1.saveDeveloper(developer);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateDeveloper() {
        ResponseEntity responseEntity = developerControllerV1.updateDeveloper(developer);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteDeveloper() {
        when(developerService.findById(developer.getId())).thenReturn(developer);
        ResponseEntity responseEntity = developerControllerV1.deleteDeveloper(developer.getId());
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer());
        when(developerService.getAll()).thenReturn(developers);
        ResponseEntity<List<Developer>> response = developerControllerV1.getAllDevelopers();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
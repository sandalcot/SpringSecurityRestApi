package com.daria.demospring.rest;

import com.daria.demospring.dto.DeveloperDto;
import com.daria.demospring.model.Developer;
import com.daria.demospring.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/developer/")
public class DeveloperControllerV1 {
    private final DeveloperService developerService;

    @Autowired
    public DeveloperControllerV1(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DeveloperDto> getDeveloper(@PathVariable("id") Long developerId) {
        Developer developer = this.developerService.findById(developerId);

        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DeveloperDto developerDto = DeveloperDto.fromDeveloper(developer);

        return new ResponseEntity<>(developerDto, HttpStatus.OK);
    }

    @PostMapping(value = "/saveDeveloper")
    public ResponseEntity<Developer> saveDeveloper(@RequestBody @Valid Developer developer) {
        HttpHeaders headers = new HttpHeaders();

        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.developerService.register(developer);
        return new ResponseEntity<>(developer, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateDeveloper")
    public ResponseEntity<Developer> updateDeveloper(@RequestBody @Valid Developer developer) {
        HttpHeaders headers = new HttpHeaders();

        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.developerService.register(developer);

        return new ResponseEntity<>(developer, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Developer> deleteDeveloper(@PathVariable("id") Long id) {
        Developer developer = this.developerService.findById(id);

        if (developer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.developerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/allDevelopers")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        List<Developer> developers = this.developerService.getAll();

        if (developers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(developers, HttpStatus.OK);
    }
}

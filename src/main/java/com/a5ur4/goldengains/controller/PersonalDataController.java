package com.a5ur4.goldengains.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.a5ur4.goldengains.dtos.PersonalDataDTO;
import com.a5ur4.goldengains.service.PersonalDataService;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {
    
    private final PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllPersonalData() {
        try {
            return ResponseEntity.ok(personalDataService.getAllPersonalData());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getPersonalDataById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personalDataService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_user_id/{userId}")
    public ResponseEntity<?> getPersonalDataByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(personalDataService.findByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPersonalData(@RequestBody PersonalDataDTO personalDataDTO) {
        try {
            if (personalDataDTO.name() == null || personalDataDTO.name().isBlank()) {
                return ResponseEntity.badRequest().body("Name cannot be null or empty");
            }
            String response = personalDataService.postPersonalData(personalDataDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Long id, @RequestBody PersonalDataDTO personalDataDTO) {
        try {
            PersonalDataDTO updatedPersonalData = personalDataService.updatePersonalDataDTO(id, personalDataDTO);
            return ResponseEntity.ok(updatedPersonalData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Long id) {
        try {
            personalDataService.deletePersonalData(id);
            return ResponseEntity.ok("Personal data deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

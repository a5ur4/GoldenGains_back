package com.a5ur4.goldengains.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.a5ur4.goldengains.dtos.LiftsDTO;
import com.a5ur4.goldengains.service.LiftsService;

@RestController
@RequestMapping("/lifts")
public class LiftsController {
    
    private final LiftsService liftsService;

    public LiftsController(LiftsService liftsService) {
        this.liftsService = liftsService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllLifts() {
        try {
            return ResponseEntity.ok(liftsService.getAllLifts());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getLiftsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(liftsService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLifts(@RequestBody LiftsDTO liftsDTO) {
        try {
            if (liftsDTO.name() == null || liftsDTO.name().isBlank()) {
                return ResponseEntity.badRequest().body("Name cannot be null or empty");
            }
            String response = liftsService.postLifts(liftsDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{liftsId}")
    public ResponseEntity<?> updateLifts(@PathVariable Long liftsId, @RequestBody LiftsDTO liftsDTO) {
        try {
            LiftsDTO updatedLifts = liftsService.updateLifts(liftsId, liftsDTO);
            return ResponseEntity.ok(updatedLifts);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{liftsId}")
    public ResponseEntity<?> deleteLifts(@PathVariable Long liftsId) {
        try {
            liftsService.deleteLifts(liftsId);
            return ResponseEntity.ok("Lifts deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
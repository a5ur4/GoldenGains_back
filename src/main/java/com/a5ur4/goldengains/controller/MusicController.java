package com.a5ur4.goldengains.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a5ur4.goldengains.dtos.MusicDTO;
import com.a5ur4.goldengains.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {
    
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllMusic() {
        try {
            return ResponseEntity.ok(musicService.getAllMusic());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getMusicById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(musicService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMusic(@RequestBody MusicDTO musicDTO) {
        try {
            if (musicDTO.title() == null || musicDTO.title().isBlank() ||
                    musicDTO.artist() == null || musicDTO.artist().isBlank()) {
                return ResponseEntity.badRequest().body("Title and artist cannot be null or empty");
            }
            String response = musicService.postMusic(musicDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{musicId}")
    public ResponseEntity<?> updateMusic(@PathVariable Long musicId, @RequestBody MusicDTO musicDTO) {
        try {
            MusicDTO updatedMusic = musicService.updateMusic(musicId, musicDTO);
            return ResponseEntity.ok(updatedMusic);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{musicId}")
    public ResponseEntity<?> deleteMusic(@PathVariable Long musicId) {
        try {
            musicService.deleteMusic(musicId);
            return ResponseEntity.ok("Music deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

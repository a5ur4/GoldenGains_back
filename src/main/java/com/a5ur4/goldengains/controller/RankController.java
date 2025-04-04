package com.a5ur4.goldengains.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.a5ur4.goldengains.dtos.RanksDTO;
import com.a5ur4.goldengains.service.RankService;

@RestController
@RequestMapping("/ranks")
public class RankController {
    
    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllRanks() {
        try {
            return ResponseEntity.ok(rankService.getAllRanks());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_lift_id/{liftId}")
    public ResponseEntity<?> getRanksByLiftId(@PathVariable Long liftId) {
        try {
            return ResponseEntity.ok(rankService.getRanksByLiftId(liftId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get_by_user_id/{userId}")
    public ResponseEntity<?> getRanksByUserId(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(rankService.getRanksByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRank(@RequestBody RanksDTO ranksDTO) {
        try {
            if (ranksDTO.position() == null || ranksDTO.position() < 1) {
                return ResponseEntity.badRequest().body("Position cannot be null or less than 1");
            }
            String response = rankService.postRank(ranksDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/update/{rankId}")
    public ResponseEntity<?> updateRank(@PathVariable Long rankId, @RequestBody RanksDTO ranksDTO) {
        try {
            RanksDTO updatedRank = rankService.updateRank(rankId, ranksDTO);
            return ResponseEntity.ok(updatedRank);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{rankId}")
    public ResponseEntity<?> deleteRank(@PathVariable Long rankId) {
        try {
            rankService.deleteRank(rankId);
            return ResponseEntity.ok("Rank deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

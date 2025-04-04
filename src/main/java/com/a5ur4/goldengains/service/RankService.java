package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.RanksDTO;
import com.a5ur4.goldengains.entity.Ranks;
import com.a5ur4.goldengains.repository.LiftsRepository;
import com.a5ur4.goldengains.repository.RanksRepository;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class RankService {
    
    private final RanksRepository ranksRepository;
    private final LiftsRepository liftsRepository;
    private final UserRepository userRepository;

    public RankService(RanksRepository ranksRepository, LiftsRepository liftsRepository, UserRepository userRepository) {
        this.ranksRepository = ranksRepository;
        this.liftsRepository = liftsRepository;
        this.userRepository = userRepository;
    }

    public String postRank(RanksDTO ranksDTO) {
        if (ranksRepository.findByUserIdAndLiftId(ranksDTO.user(), ranksDTO.lift()).isPresent()) {
            return "Rank already exists for this user and lift";
        } else {
            Ranks newRank = new Ranks();
            newRank.setPosition(ranksDTO.position());
            newRank.setLift(liftsRepository.findById(ranksDTO.lift())
                    .orElseThrow(() -> new RuntimeException("Lift not found with ID: " + ranksDTO.lift())));
            newRank.setUser(userRepository.findById(ranksDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + ranksDTO.user())));

            ranksRepository.save(newRank);
            return "Rank created successfully";
        }
    }

    public List<RanksDTO> getAllRanks() {
        return ranksRepository.findAll()
                .stream()
                .map(this::convertToRanksDTO)
                .toList();
    }

    public List<RanksDTO> getRanksByLiftId(Long liftId) {
        return ranksRepository.findByLiftId(liftId)
                .stream()
                .map(this::convertToRanksDTO)
                .toList();
    }

    public List<RanksDTO> getRanksByUserId(Long userId) {
        return ranksRepository.findByUserId(userId)
                .stream()
                .map(this::convertToRanksDTO)
                .toList();
    }

    public RanksDTO findById(Long id) {
        return ranksRepository.findById(id)
                .map(this::convertToRanksDTO)
                .orElseThrow(() -> new RuntimeException("Rank not found with ID: " + id));
    }

    public RanksDTO updateRank(Long id, RanksDTO ranksDTO) {
        Ranks existingRank = ranksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rank not found with ID: " + id));

        if (ranksDTO.position() != null) {
            existingRank.setPosition(ranksDTO.position());
        }
        if (ranksDTO.lift() != null) {
            existingRank.setLift(liftsRepository.findById(ranksDTO.lift())
                    .orElseThrow(() -> new RuntimeException("Lift not found with ID: " + ranksDTO.lift())));
        }
        if (ranksDTO.user() != null) {
            existingRank.setUser(userRepository.findById(ranksDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + ranksDTO.user())));
        }
        ranksRepository.save(existingRank);
        return convertToRanksDTO(existingRank);
    }

    public void deleteRank(Long id) {
        Ranks existingRank = ranksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rank not found with ID: " + id));
        ranksRepository.delete(existingRank);
    }

    private RanksDTO convertToRanksDTO(Ranks ranks) {
        return new RanksDTO(
                ranks.getId(),
                ranks.getPosition(),
                ranks.getLift().getId(),
                ranks.getUser().getId()
        );
    }
}

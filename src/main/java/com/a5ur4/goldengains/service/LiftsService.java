package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.LiftsDTO;
import com.a5ur4.goldengains.entity.Lifts;
import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.LiftsRepository;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class LiftsService {
    
    private final LiftsRepository liftsRepository;
    private final UserRepository userRepository;

    public LiftsService(LiftsRepository liftsRepository, UserRepository userRepository) {
        this.liftsRepository = liftsRepository;
        this.userRepository = userRepository;
    }

    public String postLifts(LiftsDTO liftsDTO) {
        if (liftsRepository.findByUserIdAndName(liftsDTO.user(), liftsDTO.name()).isPresent()) {
            return "Lifts already exist for this user";        
        } else {
            Lifts newLifts = new Lifts();
            newLifts.setName(liftsDTO.name());
            newLifts.setWeight(liftsDTO.weight());
            newLifts.setReps(liftsDTO.reps());
            newLifts.setSets(liftsDTO.sets());

            User userEntity = userRepository.findById(liftsDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + liftsDTO.user()));
            newLifts.setUser(userEntity);

            liftsRepository.save(newLifts);
            return "Lifts created successfully";
        }
    }

    public List<LiftsDTO> getAllLifts() {
        return liftsRepository.findAll()
                .stream()
                .map(this::convertToLiftsDTO)
                .toList();
    }

    public List<LiftsDTO> getLiftsByName(String name) {
        return liftsRepository.findByName(name)
                .stream()
                .map(this::convertToLiftsDTO)
                .toList();
    }

    public List<LiftsDTO> getLiftsByUserId(Long userId) {
        return liftsRepository.findByUserId(userId)
                .stream()
                .map(this::convertToLiftsDTO)
                .toList();
    }

    public LiftsDTO findById(Long id) {
        return liftsRepository.findById(id)
                .map(this::convertToLiftsDTO)
                .orElseThrow(() -> new RuntimeException("Lifts not found with ID: " + id));
    }

    public LiftsDTO updateLifts(Long id, LiftsDTO liftsDTO) {
        Lifts lifts = liftsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lifts not found with ID: " + id));

        if (liftsDTO.name() != null) {
            lifts.setName(liftsDTO.name());
        }
        if (liftsDTO.weight() != null) {
            lifts.setWeight(liftsDTO.weight());
        }
        if (liftsDTO.reps() != null) {
            lifts.setReps(liftsDTO.reps());
        }
        if (liftsDTO.sets() != null) {
            lifts.setSets(liftsDTO.sets());
        }
        if (liftsDTO.user() != null) {
            User userEntity = userRepository.findById(liftsDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + liftsDTO.user()));
            lifts.setUser(userEntity);
        }
        liftsRepository.save(lifts);
        return convertToLiftsDTO(lifts);
    }

    public String deleteLifts(Long id) {
        Lifts lifts = liftsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lifts not found with ID: " + id));
        liftsRepository.delete(lifts);
        return "Lifts deleted successfully";
    }

    private LiftsDTO convertToLiftsDTO(Lifts lifts) {
        return new LiftsDTO(
                lifts.getId(),
                lifts.getName(),
                lifts.getWeight(),
                lifts.getReps(),
                lifts.getSets(),
                lifts.getUser().getId()
        );
    }
}

package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.PersonalDataDTO;
import com.a5ur4.goldengains.entity.PersonalData;
import com.a5ur4.goldengains.repository.PersonalDataRepository;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class PersonalDataService {
    
    private final PersonalDataRepository personalDataRepository;
    private final UserRepository userRepository;

    public PersonalDataService(PersonalDataRepository personalDataRepository, UserRepository userRepository) {
        this.personalDataRepository = personalDataRepository;
        this.userRepository = userRepository;
    }

    public String postPersonalData(PersonalDataDTO personalDataDTO) {
        if (personalDataRepository.findByUserId(personalDataDTO.user()).isPresent()) {
            return "Personal data already exists for this user";
        } else {
            PersonalData newPersonalData = new PersonalData();
            newPersonalData.setUser(userRepository.findById(personalDataDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + personalDataDTO.user())));
            newPersonalData.setName(personalDataDTO.name());
            newPersonalData.setBirthday(personalDataDTO.birthday());
            newPersonalData.setPhone(personalDataDTO.phone());
            newPersonalData.setWeight(personalDataDTO.weight());
            newPersonalData.setHeight(personalDataDTO.height());
            newPersonalData.setCountry(personalDataDTO.country());

            personalDataRepository.save(newPersonalData);
            return "Personal data created successfully";
        }
    }

    public List<PersonalDataDTO> getAllPersonalData() {
        return personalDataRepository.findAll()
                .stream()
                .map(this::convertToPersonalDataDTO)
                .toList();
    }

    public PersonalDataDTO findById(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found with ID: " + id));
        return convertToPersonalDataDTO(personalData);
    }

    public PersonalDataDTO findByUserId(Long userId) {
        PersonalData personalData = personalDataRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Personal data not found for user ID: " + userId));
        return convertToPersonalDataDTO(personalData);
    }

    public PersonalDataDTO updatePersonalDataDTO(Long id, PersonalDataDTO personalDataDTO) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found with ID: " + id));

        if (personalDataDTO.name() != null) {
            personalData.setName(personalDataDTO.name());
        }
        if (personalDataDTO.birthday() != null) {
            personalData.setBirthday(personalDataDTO.birthday());
        }
        if (personalDataDTO.phone() != null) {
            personalData.setPhone(personalDataDTO.phone());
        }
        if (personalDataDTO.weight() != null) {
            personalData.setWeight(personalDataDTO.weight());
        }
        if (personalDataDTO.height() != null) {
            personalData.setHeight(personalDataDTO.height());
        }
        if (personalDataDTO.country() != null) {
            personalData.setCountry(personalDataDTO.country());
        }
        if (personalDataDTO.user() != null) {
            personalData.setUser(userRepository.findById(personalDataDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + personalDataDTO.user())));
        }
        personalDataRepository.save(personalData);
        return convertToPersonalDataDTO(personalData);
    }

    public void deletePersonalData(Long id) {
        PersonalData personalData = personalDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal data not found with ID: " + id));
        personalDataRepository.delete(personalData);
    }

    private PersonalDataDTO convertToPersonalDataDTO(PersonalData personalData) {
        return new PersonalDataDTO(
                personalData.getId(),
                personalData.getName(),
                personalData.getBirthday(),
                personalData.getPhone(),
                personalData.getWeight(),
                personalData.getHeight(),
                personalData.getCountry(),
                personalData.getUser().getId()
        );
    }
}

package com.a5ur4.goldengains.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a5ur4.goldengains.dtos.MusicDTO;
import com.a5ur4.goldengains.entity.Music;
import com.a5ur4.goldengains.entity.User;
import com.a5ur4.goldengains.repository.MusicRepository;
import com.a5ur4.goldengains.repository.UserRepository;

@Service
public class MusicService {
    
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    public MusicService(MusicRepository musicRepository, UserRepository userRepository) {
        this.musicRepository = musicRepository;
        this.userRepository = userRepository;
    }

    public String postMusic(MusicDTO musicDTO) {
        if (musicRepository.findByTitle(musicDTO.title()).isPresent()) {
            return "Music already exists";
        } else {
            Music newMusic = new Music();
            newMusic.setGenre(musicDTO.genre());
            newMusic.setArtist(musicDTO.artist());
            newMusic.setTitle(musicDTO.title());
            newMusic.setLink(musicDTO.link());

            User userEntity = userRepository.findById(musicDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + musicDTO.user()));
            newMusic.setUser(userEntity);

            musicRepository.save(newMusic);
            return "Music created successfully";
        }
    }

    public List<MusicDTO> getAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(this::convertToMusicDTO)
                .toList();
    }

    public List<MusicDTO> getMusicByGenre(String genre) {
        return musicRepository.findByGenre(genre)
                .stream()
                .map(this::convertToMusicDTO)
                .toList();
    }

    public List<MusicDTO> getMusicByArtist(String artist) {
        return musicRepository.findByArtist(artist)
                .stream()
                .map(this::convertToMusicDTO)
                .toList();
    }

    public MusicDTO findById(Long id) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found with ID: " + id));
        return convertToMusicDTO(music);
    }

    public MusicDTO updateMusic(Long id, MusicDTO musicDTO) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found with ID: " + id));

        if (musicDTO.genre() != null) {
            music.setGenre(musicDTO.genre());
        }
        if (musicDTO.artist() != null) {
            music.setArtist(musicDTO.artist());
        }
        if (musicDTO.title() != null) {
            music.setTitle(musicDTO.title());
        }
        if (musicDTO.link() != null) {
            music.setLink(musicDTO.link());
        }
        if (musicDTO.user() != null) {
            User user = userRepository.findById(musicDTO.user())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + musicDTO.user()));
            music.setUser(user);
        }
        musicRepository.save(music);
        return convertToMusicDTO(music);
    }

    public void deleteMusic(Long id) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found with ID: " + id));
        musicRepository.delete(music);
    }

    public MusicDTO convertToMusicDTO(Music music) {
        return new MusicDTO(
                music.getId(),
                music.getGenre(),
                music.getArtist(),
                music.getTitle(),
                music.getLink(),
                music.getUser().getId()
        );
    }
}

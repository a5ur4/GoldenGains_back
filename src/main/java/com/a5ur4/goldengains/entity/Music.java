package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "music")
public class Music {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String song_name;

    @Column(nullable = false)
    private String link;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Music() {}

    public Music(String genre, String artist, String song_name, String link, User user) {
        this.genre = genre;
        this.artist = artist;
        this.song_name = song_name;
        this.link = link;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

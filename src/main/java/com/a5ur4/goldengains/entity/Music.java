package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "music")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lifts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lifts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private int sets;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

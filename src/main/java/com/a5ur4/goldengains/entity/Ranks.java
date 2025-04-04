package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ranks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Integer position;

    @OneToOne
    @JoinColumn(name = "lift_id", nullable = false)
    private Lifts lift;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

package com.a5ur4.goldengains.entity;

import java.math.BigDecimal;

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
    private BigDecimal weight;

    @Column(nullable = false)
    private Integer reps;

    @Column(nullable = false)
    private Integer sets;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

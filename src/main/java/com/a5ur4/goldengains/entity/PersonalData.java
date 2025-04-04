package com.a5ur4.goldengains.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

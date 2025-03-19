package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lifts")
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
    private Users user;

    public Lifts() {}

    public Lifts(String name, double weight, int reps, int sets, Users user) {
        this.name = name;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

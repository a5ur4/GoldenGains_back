package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranks")
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

    public Ranks() {}

    public Ranks(Integer position, Lifts lift, User user) {
        this.position = position;
        this.lift = lift;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Lifts getLift() {
        return lift;
    }

    public void setLift(Lifts lift) {
        this.lift = lift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

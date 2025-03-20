package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranks")
public class Ranks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Integer postion;

    @OneToOne
    @JoinColumn(name = "lift_id", nullable = false)
    private Lifts lift;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Ranks() {}

    public Ranks(Integer postion, Lifts lift, Users user) {
        this.postion = postion;
        this.lift = lift;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Integer getPostion() {
        return postion;
    }

    public void setPostion(Integer postion) {
        this.postion = postion;
    }

    public Lifts getLift() {
        return lift;
    }

    public void setLift(Lifts lift) {
        this.lift = lift;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}

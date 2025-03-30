package com.a5ur4.goldengains.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Posts> posts;

    public Categories() {}

    public Categories(String name, String description, List<Posts> posts) {
        this.name = name;
        this.description = description;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Posts> getPost() {
        return posts;
    }

    public void setPost(List<Posts> posts) {
        this.posts = posts;
    }
}
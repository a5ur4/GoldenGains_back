package com.a5ur4.goldengains.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Posts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "created_at", nullable = false)
    private Date created_at;

    @Column(name = "upvotes", nullable = false)
    private Integer upvotes;

    @Column(name = "downvotes", nullable = false)
    private Integer downvotes;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    public Posts() {}

    public Posts(String title, String content, String image, Date created_at, Integer upvotes, Integer downvotes, User user, Categories category) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.created_at = created_at;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.user = user;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}

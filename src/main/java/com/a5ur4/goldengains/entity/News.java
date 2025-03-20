package com.a5ur4.goldengains.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String link;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    public News() {}

    public News(String title, String content, String link, Categories category) {
        this.title = title;
        this.content = content;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}

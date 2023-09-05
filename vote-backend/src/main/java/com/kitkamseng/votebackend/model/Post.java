package com.kitkamseng.votebackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String postTitle;
    private String imageUrl;
    @Column(length = 1000)
    private String postDescription;

    private Long userId;

    public Post() {
        // Default constructor (no-argument constructor) is needed for JPA
    }

    public Post(String postTitle, String postDescription, String imageUrl, Long userId) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}

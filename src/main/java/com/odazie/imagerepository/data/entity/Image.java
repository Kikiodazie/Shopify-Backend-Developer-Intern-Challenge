package com.odazie.imagerepository.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.odazie.imagerepository.business.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image extends AuditModel {


    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "image_title", nullable = false)
    private String title;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "isPublic", columnDefinition = "boolean default true")
    private boolean isPublic = true;




    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

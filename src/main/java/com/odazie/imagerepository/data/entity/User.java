package com.odazie.imagerepository.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long userId;

    @NotNull
    @Column(name = "first_name", nullable = false )
    @ApiModelProperty(required = true)
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @NotNull
    @Email
    @Column(name = "email", nullable = false, unique = true)
    @ApiModelProperty(required = true)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    @ApiModelProperty(required = true)
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private final List<Image> images = new ArrayList<>();

    public void addImage(Image image){
        images.add(image);
        image.setUser(this);
    }

    public void deleteImage(Image image){
        images.remove(image);
        image.setUser(null);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Image> getImages() {
        return images;
    }
}

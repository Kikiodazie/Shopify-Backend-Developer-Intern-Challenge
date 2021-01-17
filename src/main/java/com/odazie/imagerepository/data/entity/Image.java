package com.odazie.imagerepository.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {


    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;




    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;





}

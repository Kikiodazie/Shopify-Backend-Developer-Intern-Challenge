package com.odazie.imagerepository.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {


    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

}

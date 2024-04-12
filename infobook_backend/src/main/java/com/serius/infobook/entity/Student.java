package com.serius.infobook.entity;

import jakarta.persistence.*;

// Marks the class as an entity, meaning it will be mapped to a table in the database.
@Entity
// Name of the table in the database where objects of this entity will be stored.
@Table(name = "students")
public class Student {

    // Denotes the primary key of the entity.
    @Id
    /*
    The database will automatically generate the primary key values.
    'IDENTITY' strategy indicates that the database will automatically assign a unique primary key value
    */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 'id' is the primary key
    private long id;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private String university;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}

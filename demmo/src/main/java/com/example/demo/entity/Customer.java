package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String name;

    @Column(name = "CLIENT_EMAIL", unique = true)
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

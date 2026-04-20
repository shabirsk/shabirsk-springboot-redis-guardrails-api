package com.shaik.backend.entity;

import jakarta.persistence.*;

@Entity
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String personaDescription;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonaDescription() {
        return personaDescription;
    }

    public void setPersonaDescription(String personaDescription) {
        this.personaDescription = personaDescription;
    }
}
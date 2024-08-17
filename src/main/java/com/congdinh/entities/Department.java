package com.congdinh.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Constructors
    public Department() {
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Department(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Department(UUID id, String name, String description, Set<Employee> employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }
}

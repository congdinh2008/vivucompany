package com.congdinh.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.TimeZoneColumn;

import com.congdinh.entities.enums.ProjectStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @TimeZoneColumn
    @Column(name = "start_date")
    private LocalDate startDate;

    @TimeZoneColumn
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectEmployee> projectEmployees;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<ProjectEmployee> getProjectEmployees() {
        return projectEmployees;
    }

    public void setProjectEmployees(List<ProjectEmployee> projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    // Constructors
    public Project() {
    }

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status,
            Department department) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.department = department;
    }

    public Project(UUID id, String name, String description, LocalDate startDate, LocalDate endDate,
            ProjectStatus status,
            Department department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.department = department;
    }

}

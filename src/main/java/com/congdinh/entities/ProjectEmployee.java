package com.congdinh.entities;

import java.time.LocalDate;

import org.hibernate.annotations.TimeZoneColumn;

import jakarta.persistence.*;

@Entity
@Table(name = "project_employee")
public class ProjectEmployee {
    @EmbeddedId
    private ProjectEmployeeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    private Project project;

    @Column(name = "role", length = 255)
    private String role;

    @TimeZoneColumn
    @Column(name = "start_date")
    private LocalDate startDate;

    @TimeZoneColumn
    @Column(name = "end_date")
    private LocalDate endDate;

    // Getters and setters
    public ProjectEmployeeId getId() {
        return id;
    }

    public void setId(ProjectEmployeeId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    // Constructors
    public ProjectEmployee() {
    }

    public ProjectEmployee(ProjectEmployeeId id, Employee employee, Project project, String role, LocalDate startDate,
            LocalDate endDate) {
        this.id = id;
        this.employee = employee;
        this.project = project;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProjectEmployee(Employee employee, Project project, String role, LocalDate startDate, LocalDate endDate) {
        this.id = new ProjectEmployeeId(employee.getId(), project.getId());
        this.employee = employee;
        this.project = project;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

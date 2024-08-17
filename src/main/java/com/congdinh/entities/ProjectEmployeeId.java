package com.congdinh.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProjectEmployeeId {
    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "project_id")
    private UUID projectId;

    // Getters and setters
    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    // Constructors

    public ProjectEmployeeId() {
    }

    public ProjectEmployeeId(UUID employeeId, UUID projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProjectEmployeeId that = (ProjectEmployeeId) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) {
            return false;
        }
        return projectId != null ? projectId.equals(that.projectId) : that.projectId == null;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }
}

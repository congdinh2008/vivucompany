package com.congdinh.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private EmployeeDetail employeeDetail;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public List<ProjectEmployee> getProjectEmployees() {
        return projectEmployees;
    }

    public void setProjectEmployees(List<ProjectEmployee> projectEmployees) {
        this.projectEmployees = projectEmployees;
    }

    // Constructors
    public Employee() {
    }

    public Employee(String name, String email, String phone, Department department) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public Employee(UUID id, String name, String email, String phone, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public Employee(UUID id, String name, String email, String phone, Department department, EmployeeDetail employeeDetail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.employeeDetail = employeeDetail;
    }

    public Employee(String name, String email, String phone, Department department, EmployeeDetail employeeDetail) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.employeeDetail = employeeDetail;
    }
}

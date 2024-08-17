package com.congdinh.entities;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_details")
public class EmployeeDetail {
    @Id
    @Column(name = "employee_id")
    private UUID employeeId;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "country", length = 255)
    private String country;

    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Getters and setters
    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Constructors
    public EmployeeDetail() {
    }

    public EmployeeDetail(String address, String city, String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public EmployeeDetail(UUID employeeId, String address, String city, String country) {
        this.employeeId = employeeId;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public EmployeeDetail(String address, String city, String country, Employee employee) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.employee = employee;
    }
}

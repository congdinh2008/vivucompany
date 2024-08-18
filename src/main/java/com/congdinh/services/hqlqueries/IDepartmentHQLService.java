package com.congdinh.services.hqlqueries;

import java.util.List;
import java.util.UUID;

import com.congdinh.entities.Department;

public interface IDepartmentHQLService {
    List<Department> getAll();
    Department getById(UUID id);
    boolean create(Department department);
    boolean update(Department department);
    boolean delete(UUID id);
}

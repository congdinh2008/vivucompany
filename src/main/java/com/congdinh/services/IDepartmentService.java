package com.congdinh.services;

import java.util.List;
import java.util.UUID;

import com.congdinh.entities.Department;
import com.congdinh.services.base.IGenericServices;

public interface IDepartmentService extends IGenericServices<Department, UUID> {

    List<Department> getDepartmentByIdWithEmployees(UUID id) throws Exception;

    List<Department> getDepartmentByIdWithProjects(UUID id) throws Exception;
}

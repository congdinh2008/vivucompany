package com.congdinh.services;

import java.util.List;
import java.util.UUID;

import com.congdinh.entities.Employee;
import com.congdinh.services.base.IGenericServices;

public interface IEmployeeService extends IGenericServices<Employee, UUID> {
    List<Employee> getEmployeeByIdWithProjects(UUID id) throws Exception;

    List<Employee> getEmployeeByIdWithDepartment(UUID id) throws Exception;

    List<Employee> getEmployeeByIdWithEmployeeDetail(UUID id) throws Exception;
}

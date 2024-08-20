package com.congdinh.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.congdinh.entities.Employee;
import com.congdinh.services.base.GenericServices;

public class EmployeeService extends GenericServices<Employee, UUID> implements IEmployeeService {
    @Override
    public List<Employee> getEmployeeByIdWithProjects(UUID id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            // Fetch employee with projects by id through ProjectEmployee entity
            return session
                    .createQuery(
                            "SELECT e FROM Employee e JOIN FETCH e.projectEmployees pe JOIN FETCH pe.project WHERE e.id = :id",
                            Employee.class)
                    .setParameter("id", id).getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public List<Employee> getEmployeeByIdWithDepartment(UUID id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT e FROM Employee e JOIN FETCH e.department WHERE e.id = :id", Employee.class)
                    .setParameter("id", id).getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public List<Employee> getEmployeeByIdWithEmployeeDetail(UUID id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT e FROM Employee e JOIN FETCH e.employeeDetail WHERE e.id = :id",
                            Employee.class)
                    .setParameter("id", id).getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }
}

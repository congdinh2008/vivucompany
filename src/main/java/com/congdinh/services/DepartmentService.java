package com.congdinh.services;

import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.congdinh.entities.Department;
import com.congdinh.services.base.GenericServices;

public class DepartmentService extends GenericServices<Department, UUID> implements IDepartmentService {

    @Override
    public List<Department> getDepartmentByIdWithEmployees(UUID id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT d FROM Department d JOIN FETCH d.employees WHERE d.id = :id", Department.class)
                    .setParameter("id", id).getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public List<Department> getDepartmentByIdWithProjects(UUID id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT d FROM Department d JOIN FETCH d.projects WHERE d.id = :id", Department.class)
                    .setParameter("id", id).getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }
}

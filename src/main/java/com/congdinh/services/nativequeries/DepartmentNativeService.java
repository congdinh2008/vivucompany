package com.congdinh.services.nativequeries;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.congdinh.entities.Department;
import com.congdinh.utils.HibernateUtils;

import jakarta.persistence.NoResultException;

public class DepartmentNativeService implements IDepartmentNativeService {

    protected SessionFactory sessionFactory;

    public DepartmentNativeService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Department> getAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Department> departments = session.createNativeQuery("SELECT * FROM departments", Department.class)
                    .getResultList();
            transaction.commit();
            return departments;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Department getById(UUID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Department department = session.createNativeQuery("SELECT * FROM departments WHERE id = :id", Department.class)
                    .setParameter("id", id).uniqueResult();
            transaction.commit();
            return department;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean create(Department department) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Create using native query
            NativeQuery<Department> query = session.createNativeQuery(
                    "INSERT INTO departments (id, name, description) VALUES (:id, :name, :description)");
            query.setParameter("id", department.getId());
            query.setParameter("name", department.getName());
            query.setParameter("description", department.getDescription());
            var result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Update using native query
            NativeQuery<Department> query = session.createNativeQuery(
                    "UPDATE departments SET name = :name, description = :description WHERE id = :id");
            query.setParameter("id", department.getId());
            query.setParameter("name", department.getName());
            query.setParameter("description", department.getDescription());
            var result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(UUID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Delete using native query
            NativeQuery<Department> query = session.createNativeQuery("DELETE FROM departments WHERE id = :id");
            query.setParameter("id", id);
            var result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}

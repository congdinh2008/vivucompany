package com.congdinh.services.hqlqueries;

import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.congdinh.entities.Department;
import com.congdinh.utils.HibernateUtils;

public class DepartmentHQLService implements IDepartmentHQLService {
    protected SessionFactory sessionFactory;

    public DepartmentHQLService() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Department> getAll() {
        Transaction transaction = null;
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Department> departments = session.createQuery("FROM Department", Department.class).getResultList();
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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Department department = session.createQuery("FROM Department WHERE id = :id", Department.class)
                    .setParameter("id", id).uniqueResult();
            transaction.commit();
            return department;
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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Create a new department using HQL query
            Query<Department> query = session
                    .createQuery("INSERT INTO Department (id, name, description) VALUES (:id, :name, :description)");
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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Update a department using HQL query
            Query<Department> query = session
                    .createQuery("UPDATE Department SET name = :name, description = :description WHERE id = :id");
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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Delete a department using HQL query
            Query<Department> query = session.createQuery("DELETE FROM Department WHERE id = :id");
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

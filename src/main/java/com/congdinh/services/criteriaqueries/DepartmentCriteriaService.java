package com.congdinh.services.criteriaqueries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.congdinh.entities.Department;
import com.congdinh.utils.HibernateUtils;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;

public class DepartmentCriteriaService implements IDepartmentCriteriaService {
    protected SessionFactory sessionFactory;
    public DepartmentCriteriaService() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Department> getAll() {
        Transaction transaction = null;
        List<Department> departments = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Using Criteria Query
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
            criteria.from(Department.class);
            departments = session.createQuery(criteria).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department getById(UUID id) {
        Transaction transaction = null;
        Department department = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            // Using Criteria Query
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteria = builder.createQuery(Department.class);
            criteria.where(builder.equal(criteria.from(Department.class).get("id"), id));
            department = session.createQuery(criteria).getSingleResult();
            transaction.commit();
        } catch(NoResultException e) {
            return null;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public boolean create(Department department) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(department);
            transaction.commit();
            return true;
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
            // Using Criteria Update
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Department> criteria = builder.createCriteriaUpdate(Department.class);
            criteria.set("name", department.getName());
            criteria.set("description", department.getDescription());
            criteria.where(builder.equal(criteria.from(Department.class).get("id"), department.getId()));
            var result = session.createQuery(criteria).executeUpdate();
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
            Department department = session.get(Department.class, id);
            if (department != null) {
                // Using Criteria Delete
                CriteriaBuilder builder = session.getCriteriaBuilder();
                var criteria = builder.createCriteriaDelete(Department.class);
                criteria.where(builder.equal(criteria.from(Department.class).get("id"), id));
                var result = session.createQuery(criteria).executeUpdate();
                transaction.commit();
                return result > 0;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}

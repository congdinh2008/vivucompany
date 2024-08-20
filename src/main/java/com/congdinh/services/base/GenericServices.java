package com.congdinh.services.base;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.congdinh.utils.GenericTypeResolver;
import com.congdinh.utils.HibernateUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class GenericServices<T, ID extends Serializable> implements IGenericServices<T, ID> {

    private final Class<T> entityClass;
    protected final SessionFactory sessionFactory;

    public GenericServices() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveGenericType(getClass());
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public T getById(ID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            // Using CriteriaQuery to get all entities
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(entityClass);
            Root<T> root = criteria.from(entityClass);
            criteria.select(root);
            Query<T> query = session.createQuery(criteria);
            return query.getResultList();
        } catch (HibernateException e) {
            throw e;
        }
    }

    @Override
    public void update(ID id, T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T entityToUpdate = session.get(entityClass, id);
            if (entityToUpdate == null) {
                throw new HibernateException("Entity not found");
            }
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void delete(ID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(entityClass, id);
            if (entity == null) {
                throw new HibernateException("Entity not found");
            }
            session.remove(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}

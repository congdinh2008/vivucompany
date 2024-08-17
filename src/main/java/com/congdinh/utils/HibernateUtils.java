package com.congdinh.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for managing Hibernate sessions and session factories.
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds and returns a SessionFactory object.
     *
     * @return the SessionFactory object
     * @throws ExceptionInInitializerError if the initial SessionFactory creation
     *                                     fails
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Returns the SessionFactory object.
     *
     * @return the SessionFactory object.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Shuts down the Hibernate session factory.
     * This method closes the caches and connection pools associated with the
     * session factory.
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}

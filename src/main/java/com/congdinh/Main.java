package com.congdinh;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.congdinh.entities.Department;
import com.congdinh.entities.Employee;
import com.congdinh.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        // Create Department and Employee objects
        Department department = new Department("IT", "Information Technology");
        Employee employee1 = new Employee("Cong Dinh", "cong@domain.com", "0987654321", department);
        Employee employee2 = new Employee("Thang Nguyen", "thang@domain.com", "0123456789", department);
        Employee employee3 = new Employee("An Dinh", "an@domain.com", "0123456789", department);

        Department department2 = new Department("HR", "Human Resources");
        Employee employee4 = new Employee("Van Nguyen", "van@domain.com", "0123456789", department2);
        Employee employee5 = new Employee("Linh Tong", "linh@domain.com", "0123456789", department2);
        Employee employee6 = new Employee("Yen Duong", "yen@domain.com", "0123456789", department2);

        // Add employees to the department
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);
            session.persist(employee4);
            session.persist(employee5);
            session.persist(employee6);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
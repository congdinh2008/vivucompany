package com.congdinh;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.congdinh.entities.Department;
import com.congdinh.entities.Employee;
import com.congdinh.entities.EmployeeDetail;
import com.congdinh.utils.HibernateUtils;

public class Main {
        public static void main(String[] args) {
                // Create Department and Employee objects
                Department department = new Department("IT", "Information Technology");

                Employee employee1 = new Employee("Cong Dinh", "cong@domain.com", "0987654321", department);
                EmployeeDetail employeeDetail1 = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", employee1);
                employee1.setEmployeeDetail(employeeDetail1);

                Employee employee2 = new Employee("Thang Nguyen", "thang@domain.com", "0123456789", department);
                EmployeeDetail employeeDetail2 = new EmployeeDetail("Hung Ha", "Thai Binh", "Vietnam", employee2);
                employee2.setEmployeeDetail(employeeDetail2);

                Employee employee3 = new Employee("An Dinh", "an@domain.com", "0123453789", department);
                EmployeeDetail employeeDetail3 = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", employee3);
                employee3.setEmployeeDetail(employeeDetail3);

                Department department2 = new Department("HR", "Human Resources");

                Employee employee4 = new Employee("Van Nguyen", "van@domain.com", "0126456789", department2);
                EmployeeDetail employeeDetail4 = new EmployeeDetail("Hung Ha", "Thai Binh", "Vietnam", employee4);
                employee4.setEmployeeDetail(employeeDetail4);

                Employee employee5 = new Employee("Linh Tong", "linh@domain.com", "0123452789", department2);
                EmployeeDetail employeeDetail5 = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", employee5);
                employee5.setEmployeeDetail(employeeDetail5);

                Employee employee6 = new Employee("Yen Duong", "yen@domain.com", "0123446789", department2);
                EmployeeDetail employeeDetail6 = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", employee6);
                employee6.setEmployeeDetail(employeeDetail6);

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
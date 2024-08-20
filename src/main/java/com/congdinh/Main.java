package com.congdinh;

import java.time.LocalDate;

import org.hibernate.Session;

import com.congdinh.entities.Department;
import com.congdinh.entities.Employee;
import com.congdinh.entities.EmployeeDetail;
import com.congdinh.entities.Project;
import com.congdinh.entities.ProjectEmployee;
import com.congdinh.entities.enums.ProjectStatus;
import com.congdinh.services.DepartmentService;
import com.congdinh.services.EmployeeService;
import com.congdinh.services.ProjectService;
import com.congdinh.utils.HibernateUtils;
import com.congdinh.services.IDepartmentService;
import com.congdinh.services.IEmployeeService;
import com.congdinh.services.IProjectEmployeeService;
import com.congdinh.services.IProjectService;
import com.congdinh.services.ProjectEmployeeService;

public class Main {

        public static void main(String[] args) {

                // #region Create Departments

                // Create Department
                IDepartmentService departmentService = new DepartmentService();

                // Create Department and Employee objects
                Department it = new Department("IT", "Information Technology");
                Department hr = new Department("HR", "Human Resources");

                // Save Department
                System.out.println("Insert Department");
                try {
                        if (departmentService.getAll().size() <= 0) {
                                departmentService.create(it);
                                departmentService.create(hr);
                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }

                // #endregion

                // #region Create Employees

                // Create Employee and EmployeeDetail objects
                IEmployeeService employeeService = new EmployeeService();

                try {
                        it = departmentService.getById(it.getId());
                        hr = departmentService.getById(hr.getId());
                } catch (Exception e) {
                        e.printStackTrace();
                }

                Employee cong = new Employee("Cong Dinh", "cong@domain.com", "0987654321", it);
                EmployeeDetail congDetail = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", cong);
                cong.setEmployeeDetail(congDetail);

                Employee thang = new Employee("Thang Nguyen", "thang@domain.com", "0123456789", it);
                EmployeeDetail thangDetail = new EmployeeDetail("Hung Ha", "Thai Binh", "Vietnam", thang);
                thang.setEmployeeDetail(thangDetail);

                Employee an = new Employee("An Dinh", "an@domain.com", "0123453789", it);
                EmployeeDetail anDetail = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", an);
                an.setEmployeeDetail(anDetail);

                Employee van = new Employee("Van Nguyen", "van@domain.com", "0126456789", hr);
                EmployeeDetail vanDetail = new EmployeeDetail("Hung Ha", "Thai Binh", "Vietnam", van);
                van.setEmployeeDetail(vanDetail);

                Employee linh = new Employee("Linh Tong", "linh@domain.com", "0123452789", hr);
                EmployeeDetail linhDetail = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", linh);
                linh.setEmployeeDetail(linhDetail);

                Employee yen = new Employee("Yen Duong", "yen@domain.com", "0123446789", hr);
                EmployeeDetail yenDetail = new EmployeeDetail("Y Yen", "Nam Dinh", "Vietnam", yen);
                yen.setEmployeeDetail(yenDetail);

                // Save Employee
                System.out.println("Insert Employee with EmployeeDetail");
                try {
                        if (employeeService.getAll().size() <= 0) {
                                employeeService.create(cong);
                                employeeService.create(thang);
                                employeeService.create(an);
                                employeeService.create(van);
                                employeeService.create(linh);
                                employeeService.create(yen);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // #endregion

                // #region Create Projects

                // Create Project belonging to the department
                IProjectService projectService = new ProjectService();

                Project project1 = new Project("Setup 10 PC for employee", "Description 1",
                                LocalDate.now(),
                                LocalDate.now().plusDays(30), ProjectStatus.IN_PROGRESS, it);

                Project project2 = new Project("Migration HR System", "Description 2",
                                LocalDate.now(),
                                LocalDate.now().plusDays(60),
                                ProjectStatus.PENDING, it);

                Project project3 = new Project("Headhunting 10 IT Engineers", "Description 3",
                                LocalDate.now(),
                                LocalDate.now().plusDays(90), ProjectStatus.COMPLETED, hr);

                // Save Project
                System.out.println("Insert Projects");
                try {
                        if (projectService.getAll().size() <= 0) {
                                projectService.create(project1);
                                projectService.create(project2);
                                projectService.create(project3);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // #endregion

                // #region Create ProjectEmployees

                // Create ProjectEmployee objects
                IProjectEmployeeService projectEmployeeService = new ProjectEmployeeService();

                // Employee 1 works on Project 1
                ProjectEmployee projectEmployee1 = new ProjectEmployee(cong, project1, "Developer",
                                LocalDate.now(), LocalDate.now().plusDays(30));

                // Employee 2 works on Project 1
                ProjectEmployee projectEmployee2 = new ProjectEmployee(thang, project1, "Tester", LocalDate.now(),
                                LocalDate.now().plusDays(30));

                // Employee 3 works on Project 2
                ProjectEmployee projectEmployee3 = new ProjectEmployee(an, project2, "HR", LocalDate.now(),
                                LocalDate.now().plusDays(60));

                // Employee 4 works on Project 3
                ProjectEmployee projectEmployee4 = new ProjectEmployee(van, project3, "Recruiter",
                                LocalDate.now(),
                                LocalDate.now().plusDays(90));

                // Employee 5 works on Project 3
                ProjectEmployee projectEmployee5 = new ProjectEmployee(linh, project3, "Recruiter",
                                LocalDate.now(),
                                LocalDate.now().plusDays(90));

                // Employee 6 works on Project 3
                ProjectEmployee projectEmployee6 = new ProjectEmployee(yen, project3, "Recruiter",
                                LocalDate.now(),
                                LocalDate.now().plusDays(90));

                // Save ProjectEmployee
                System.out.println("Insert ProjectEmployee");
                try {
                        if (projectEmployeeService.getAll().size() <= 0) {
                                projectEmployeeService.create(projectEmployee1);
                                projectEmployeeService.create(projectEmployee2);
                                projectEmployeeService.create(projectEmployee3);
                                projectEmployeeService.create(projectEmployee4);
                                projectEmployeeService.create(projectEmployee5);
                                projectEmployeeService.create(projectEmployee6);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // #endregion

                // Get all Department objects

                System.out.println("All Departments:");
                try {
                        departmentService.getAll().forEach(d -> {
                                System.out.println(d.getName());
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // Get Department by Id with Employees

                System.out.println("Department by Id with Employees:");
                try {
                        Department department = departmentService.getDepartmentByIdWithEmployees(it.getId()).get(0);
                        System.out.println(department.getName());
                        department.getEmployees().forEach(e -> {
                                System.out.println(e.getName());
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Get Employee by Id with Projects:");
                try {
                        Employee employee = employeeService.getEmployeeByIdWithProjects(cong.getId()).get(0);
                        System.out.println(employee.getName());
                        employee.getProjectEmployees().forEach(pe -> {
                                System.out.println(pe.getProject().getName());
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                }

                System.out.println("Get Project by Id with Employees without method fetch:");
                try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                        Project project = session.get(Project.class, project1.getId());
                        System.out.println(project.getName());
                        project.getProjectEmployees().forEach(pe -> {
                                System.out.println(pe.getEmployee().getName());
                        });
                } catch (Exception e) {
                        e.printStackTrace();

                }

                System.out.println("Get Employee by Id with Projects without method fetch:");
                try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                        Employee employee = session.get(Employee.class, cong.getId());
                        System.out.println(employee.getName());
                        employee.getProjectEmployees().forEach(pe -> {
                                System.out.println(pe.getProject().getName());
                        });
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
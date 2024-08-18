package hqlqueries;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.congdinh.entities.Department;
import com.congdinh.services.hqlqueries.DepartmentHQLService;

public class MockDepartmentHQLServiceTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private DepartmentHQLService departmentService;

    private Department testDepartment;

    private List<Department> departments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        testDepartment = new Department();
        testDepartment.setId(UUID.randomUUID());
        testDepartment.setName("Test Department");
        testDepartment.setDescription("Test Description");

        departments = List.of(testDepartment);
    }

    @Test
    public void testGetAllDepartments() {
        Query<Department> query = mock(Query.class);
        when(session.createQuery("FROM Department", Department.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(departments);

        var actual = departmentService.getAll();
        assertNotNull(actual);
        assertEquals(departments.size(), actual.size());

        // Verify the method is called
        verify(session).createQuery("FROM Department", Department.class);
        verify(query).getResultList();
    }

    @Test
    public void testGetDepartmentById() {
        Query<Department> query = mock(Query.class);
        when(session.createQuery("FROM Department WHERE id = :id", Department.class)).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(testDepartment);

        var actual = departmentService.getById(testDepartment.getId());
        assertNotNull(actual);
        assertEquals(testDepartment.getName(), actual.getName());
        assertEquals(testDepartment.getDescription(), actual.getDescription());

        // Verify the method is called
        verify(session).createQuery("FROM Department WHERE id = :id", Department.class);
        verify(query).setParameter("id", testDepartment.getId());
        verify(query).uniqueResult();
    }

    @Test
    public void testCreateDepartment() {
        Query<Department> query = mock(Query.class);
        when(session.createQuery("INSERT INTO Department (id, name, description) VALUES (:id, :name, :description)"))
                .thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.setParameter("name", testDepartment.getName())).thenReturn(query);
        when(query.setParameter("description", testDepartment.getDescription())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        // Verify the method is called
        verify(session).createQuery("INSERT INTO Department (id, name, description) VALUES (:id, :name, :description)");
        verify(query).setParameter("id", testDepartment.getId());
        verify(query).setParameter("name", testDepartment.getName());
        verify(query).setParameter("description", testDepartment.getDescription());
        verify(query).executeUpdate();
    }

    @Test
    public void testUpdateDepartment() {
        Query<Department> query = mock(Query.class);
        when(session.createQuery("UPDATE Department SET name = :name, description = :description WHERE id = :id"))
                .thenReturn(query);
        when(query.setParameter("name", testDepartment.getName())).thenReturn(query);
        when(query.setParameter("description", testDepartment.getDescription())).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.update(testDepartment);
        assertTrue(actual);

        // Verify the method is called
        verify(session).createQuery("UPDATE Department SET name = :name, description = :description WHERE id = :id");
        verify(query).setParameter("name", testDepartment.getName());
        verify(query).setParameter("description", testDepartment.getDescription());
        verify(query).setParameter("id", testDepartment.getId());
        verify(query).executeUpdate();
    }

    @Test
    public void testDeleteDepartment() {
        Query<Department> query = mock(Query.class);
        when(session.createQuery("DELETE FROM Department WHERE id = :id")).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.delete(testDepartment.getId());
        assertTrue(actual);

        // Verify the method is called
        verify(session).createQuery("DELETE FROM Department WHERE id = :id");
        verify(query).setParameter("id", testDepartment.getId());
        verify(query).executeUpdate();
    }

}

package nativequeries;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.hibernate.*;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.congdinh.entities.Department;
import com.congdinh.services.nativequeries.DepartmentNativeService;

public class MockDepartmentNativeServiceTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private DepartmentNativeService departmentService;

    private Department testDepartment;

    private List<Department> departments;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
        NativeQuery<Department> query = mock(NativeQuery.class);
        when(session.createNativeQuery("SELECT * FROM departments", Department.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(departments);

        var actual = departmentService.getAll();
        assertNotNull(actual);
        assertEquals(departments.size(), actual.size());

        // Verify the method is called
        verify(session, times(1)).createNativeQuery("SELECT * FROM departments", Department.class);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void testGetDepartmentById() {
        NativeQuery<Department> query = mock(NativeQuery.class);
        when(session.createNativeQuery("SELECT * FROM departments WHERE id = :id", Department.class)).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(testDepartment);

        var actual = departmentService.getById(testDepartment.getId());
        assertNotNull(actual);
        assertEquals(testDepartment.getId(), actual.getId());
        assertEquals(testDepartment.getName(), actual.getName());
        assertEquals(testDepartment.getDescription(), actual.getDescription());

        // Verify the method is called
        verify(session, times(1)).createNativeQuery("SELECT * FROM departments WHERE id = :id", Department.class);
        verify(query, times(1)).setParameter("id", testDepartment.getId());
        verify(query, times(1)).uniqueResult();
    }

    @Test
    public void testCreateDepartment() {
        NativeQuery<Department> query = mock(NativeQuery.class);
        when(session.createNativeQuery(
                "INSERT INTO departments (id, name, description) VALUES (:id, :name, :description)"))
                .thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.setParameter("name", testDepartment.getName())).thenReturn(query);
        when(query.setParameter("description", testDepartment.getDescription())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.create(testDepartment);
        assert (actual);

        // Verify the method is called
        verify(session, times(1)).createNativeQuery(
                "INSERT INTO departments (id, name, description) VALUES (:id, :name, :description)");
        verify(query, times(1)).setParameter("id", testDepartment.getId());
        verify(query, times(1)).setParameter("name", testDepartment.getName());
        verify(query, times(1)).setParameter("description", testDepartment.getDescription());
        verify(query, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateDepartment() {
        NativeQuery<Department> query = mock(NativeQuery.class);
        when(session
                .createNativeQuery("UPDATE departments SET name = :name, description = :description WHERE id = :id"))
                .thenReturn(query);
        when(query.setParameter("name", testDepartment.getName())).thenReturn(query);
        when(query.setParameter("description", testDepartment.getDescription())).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.update(testDepartment);
        assert (actual);

        // Verify the method is called
        verify(session, times(1))
                .createNativeQuery("UPDATE departments SET name = :name, description = :description WHERE id = :id");
        verify(query, times(1)).setParameter("name", testDepartment.getName());
        verify(query, times(1)).setParameter("description", testDepartment.getDescription());
        verify(query, times(1)).setParameter("id", testDepartment.getId());
        verify(query, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteDepartment() {
        NativeQuery<Department> query = mock(NativeQuery.class);
        when(session.createNativeQuery("DELETE FROM departments WHERE id = :id")).thenReturn(query);
        when(query.setParameter("id", testDepartment.getId())).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        var actual = departmentService.delete(testDepartment.getId());
        assert (actual);

        // Verify the method is called
        verify(session, times(1)).createNativeQuery("DELETE FROM departments WHERE id = :id");
        verify(query, times(1)).setParameter("id", testDepartment.getId());
        verify(query, times(1)).executeUpdate();
    }
}

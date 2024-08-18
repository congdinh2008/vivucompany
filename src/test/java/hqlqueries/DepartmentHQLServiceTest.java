package hqlqueries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.*;

import com.congdinh.entities.Department;
import com.congdinh.services.hqlqueries.DepartmentHQLService;
import com.congdinh.services.hqlqueries.IDepartmentHQLService;

import jakarta.transaction.Transactional;

public class DepartmentHQLServiceTest {
    private IDepartmentHQLService departmentService;
    private Department testDepartment;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentHQLService();
        testDepartment = new Department();
        testDepartment.setId(UUID.randomUUID());
        testDepartment.setName("Test Department");
        testDepartment.setDescription("Test Description");
    }

    @Test
    @Transactional
    public void testGetAllDepartments() {
        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        var departments = departmentService.getAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);

        // Clean up
        departmentService.delete(testDepartment.getId());
    }

    @Test
    @Transactional
    public void testGetDepartmentById() {
        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        var department = departmentService.getById(testDepartment.getId());
        assertNotNull(department);
        assertEquals(testDepartment.getName(), department.getName());
        assertEquals(testDepartment.getDescription(), department.getDescription());

        // Clean up
        departmentService.delete(department.getId());
    }

    @Test
    @Transactional
    public void testCreateDepartment() {
        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        var department = departmentService.getById(testDepartment.getId());
        assertNotNull(department);
        assertEquals(testDepartment.getName(), department.getName());
        assertEquals(testDepartment.getDescription(), department.getDescription());

        // Clean up
        departmentService.delete(department.getId());
    }

    @Test
    @Transactional
    public void testUpdateDepartment() {
        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        var department = departmentService.getById(testDepartment.getId());
        assertNotNull(department);
        assertEquals(testDepartment.getName(), department.getName());
        assertEquals(testDepartment.getDescription(), department.getDescription());

        department.setName("Updated Department");
        department.setDescription("Updated Description");
        actual = departmentService.update(department);
        assertTrue(actual);

        department = departmentService.getById(testDepartment.getId());
        assertNotNull(department);
        assertEquals("Updated Department", department.getName());
        assertEquals("Updated Description", department.getDescription());

        // Clean up
        departmentService.delete(department.getId());
    }

    @Test
    @Transactional
    public void testDeleteDepartment() {
        var actual = departmentService.create(testDepartment);
        assertTrue(actual);

        var department = departmentService.getById(testDepartment.getId());
        assertNotNull(department);
        assertEquals(testDepartment.getName(), department.getName());
        assertEquals(testDepartment.getDescription(), department.getDescription());

        actual = departmentService.delete(department.getId());
        assertTrue(actual);

        department = departmentService.getById(testDepartment.getId());
        assertNull(department);
    }

    @AfterEach
    public void tearDown() {
        departmentService = null;
        testDepartment = null;
    }
}
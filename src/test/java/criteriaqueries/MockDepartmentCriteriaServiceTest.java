package criteriaqueries;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaDelete;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaCriteriaUpdate;
import org.hibernate.query.criteria.JpaPredicate;
import org.hibernate.query.criteria.JpaRoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.congdinh.entities.Department;
import com.congdinh.services.criteriaqueries.DepartmentCriteriaService;

public class MockDepartmentCriteriaServiceTest {
    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private HibernateCriteriaBuilder builder;

    @Mock
    private JpaCriteriaQuery<Department> criteria;

    @Mock
    private JpaCriteriaUpdate<Department> criteriaUpdate;

    @Mock
    private JpaCriteriaDelete<Department> criteriaDelete;

    @Mock
    private JpaPredicate predicate;

    @Mock
    private JpaRoot<Department> root;

    @Mock
    private Query<Department> query;

    @InjectMocks
    private DepartmentCriteriaService departmentService;

    private Department testDepartment;

    private List<Department> departments;

    private UUID id;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

        id = UUID.randomUUID();

        testDepartment = new Department();
        testDepartment.setId(id);
        testDepartment.setName("Test Department");
        testDepartment.setDescription("Test Description");

        departments = List.of(testDepartment);
    }

    @Test
    public void testGetAllDepartments() {
        when(session.getCriteriaBuilder()).thenReturn(builder);
        when(builder.createQuery(Department.class)).thenReturn(criteria);
        when(criteria.from(Department.class)).thenReturn(null); // Mock the from method
        when(session.createQuery(criteria)).thenReturn(query);
        when(query.getResultList()).thenReturn(departments);

        var actual = departmentService.getAll();
        assertNotNull(actual);
        assertEquals(departments.size(), actual.size());

        verify(session).getCriteriaBuilder();
        verify(builder).createQuery(Department.class);
        verify(criteria).from(Department.class);
        verify(session).createQuery(criteria);
        verify(query).getResultList();
    }

    @Test
    public void testGetDepartmentById() {
        when(session.getCriteriaBuilder()).thenReturn(builder);
        when(builder.createQuery(Department.class)).thenReturn(criteria);
        when(criteria.from(Department.class)).thenReturn(root);
        when(builder.equal(root.get("id"), id)).thenReturn(null); // Mock the equal method
        when(session.createQuery(criteria)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(testDepartment);

        var actual = departmentService.getById(id);
        assertNotNull(actual);
        assertEquals(testDepartment.getId(), actual.getId());
        assertEquals(testDepartment.getName(), actual.getName());
        assertEquals(testDepartment.getDescription(), actual.getDescription());

        verify(session).getCriteriaBuilder();
        verify(builder).createQuery(Department.class);
        verify(criteria).from(Department.class);
        verify(builder).equal(root.get("id"), id);
        verify(session).createQuery(criteria);
        verify(query).getSingleResult();
    }

    @Test
    public void testCreateDepartment() {
        // Mock the persist method
        var result = departmentService.create(testDepartment);
        assertTrue(result);

        verify(session).persist(testDepartment);
    }

    @Test
    public void testUpdateDepartment() {
        when(session.getCriteriaBuilder()).thenReturn(builder);
        when(builder.createCriteriaUpdate(Department.class)).thenReturn(criteriaUpdate);
        when(criteriaUpdate.set("name", testDepartment.getName())).thenReturn(criteriaUpdate);
        when(criteriaUpdate.set("description", testDepartment.getDescription())).thenReturn(criteriaUpdate);
        when(criteriaUpdate.from(Department.class)).thenReturn(root);
        when(builder.equal(root.get("id"), testDepartment.getId())).thenReturn(predicate);
        when(criteriaUpdate.where(predicate)).thenReturn(criteriaUpdate);
        when(session.createQuery(criteriaUpdate)).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        testDepartment.setName("Updated Department");
        testDepartment.setDescription("Updated Description");

        var actual = departmentService.update(testDepartment);
        assertTrue(actual);

        verify(session).getCriteriaBuilder();
        verify(builder).createCriteriaUpdate(Department.class);
        verify(criteriaUpdate).set("name", testDepartment.getName());
        verify(criteriaUpdate).set("description", testDepartment.getDescription());
        verify(criteriaUpdate).from(Department.class);
        verify(builder).equal(root.get("id"), testDepartment.getId());
        verify(criteriaUpdate).where(predicate);
        verify(session).createQuery(criteriaUpdate);
        verify(query).executeUpdate();
    }

    @Test
    public void testDeleteDepartment() {
        when(session.getCriteriaBuilder()).thenReturn(builder);
        when(builder.createCriteriaDelete(Department.class)).thenReturn(criteriaDelete);
        when(criteriaDelete.from(Department.class)).thenReturn(root);
        when(builder.equal(root.get("id"), id)).thenReturn(predicate);
        when(criteriaDelete.where(predicate)).thenReturn(criteriaDelete);
        when(session.createQuery(criteriaDelete)).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
        when(session.get(Department.class, id)).thenReturn(testDepartment);

        var actual = departmentService.delete(id);
        assertTrue(actual);

        verify(session).getCriteriaBuilder();
        verify(builder).createCriteriaDelete(Department.class);
        verify(criteriaDelete).from(Department.class);
        verify(builder).equal(root.get("id"), id);
        verify(criteriaDelete).where(predicate);
        verify(session).createQuery(criteriaDelete);
        verify(query).executeUpdate();
        verify(session).get(Department.class, id);
    }
}
package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.environment.Generator;
import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.Role;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CategoryServiceTest {

    @Autowired
    CategoryService sut;
    @PersistenceContext
    private EntityManager em;

    @Before
    public void setUp() {
        User admin = Generator.generateStudent();
        admin.setRole(Role.STUDENT);

    }

    @Test
    public void addCategoryAddsCategory() {
        Event event = Generator.generateEvent();
        em.persist(event);
        Category category = Generator.generateCategory();
        em.persist(category);
        assertTrue(event.getCategories().size() == 0);
        sut.addEvent(category, event);
        assertEquals(event.getCategories().size(), 1);
        Category category1 = Generator.generateCategory();
        em.persist(category1);
        String category1name = category1.getName();
        sut.addEvent(category1, event);
        assertEquals(event.getCategories().get(1).getName(), category1name);

    }

    @Test
    public void removeCategoryRemovesCategory() {
        Event event = Generator.generateEvent();
        em.persist(event);
        Category category = Generator.generateCategory();
        em.persist(category);
        sut.addEvent(category, event);
        Category category1 = Generator.generateCategory();
        em.persist(category1);
        sut.addEvent(category1, event);
        assertEquals(event.getCategories().size(), 2);
        sut.removeEvent(category, event);
        assertEquals(event.getCategories().size(), 1);

    }
}

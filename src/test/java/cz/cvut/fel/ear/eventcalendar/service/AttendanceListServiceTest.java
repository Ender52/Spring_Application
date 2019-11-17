package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.environment.Generator;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import cz.cvut.fel.ear.eventcalendar.model.EventState;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class AttendanceListServiceTest {


    @Autowired
    AttendanceListService sut;
    @PersistenceContext
    private EntityManager em;


    @Test
    public void addAttendanceEventAddsAttendanceEvent() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        assertEquals(user.getAttendanceList().size(), 0);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);

    }

    @Test
    public void removeCategoryRemovesCategory() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        sut.removeEvent(user.getAttendanceList(), event);
        assertEquals(user.getAttendanceList().size(), 0);


    }
}

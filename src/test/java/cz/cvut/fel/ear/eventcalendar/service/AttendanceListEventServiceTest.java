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
public class AttendanceListEventServiceTest {

    @Autowired
    AttendanceListEventService sut;
    @PersistenceContext
    private EntityManager em;


   /* @Test
    public void addAttendanceEventAddsAttendanceEvent() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        assertEquals(user.getAttendanceList().size(), 0);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        AttendanceListEvent anotherEvent = Generator.generateAttendanceListEvent();
        em.persist(anotherEvent);

        sut.addEvent(user.getAttendanceList(), anotherEvent, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 2);


    }


    @Test
    public void addAttendanceEventAddsSameAttendanceEvent() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        assertEquals(user.getAttendanceList().size(), 0);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);

    }


    @Test
    public void addAttendanceEventAddsSameAttendanceEventButDifferentState() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        assertEquals(user.getAttendanceList().size(), 0);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        sut.addEvent(user.getAttendanceList(), event, EventState.GOING);
        assertEquals(user.getAttendanceList().size(), 1);

    }

    @Test
    public void removeEventRemovesEvent() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        sut.removeEvent(user.getAttendanceList(), event);
        assertEquals(user.getAttendanceList().size(), 0);


    }

    @Test
    public void removeEventRemovesNotExistingEvent() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        sut.addEvent(user.getAttendanceList(), event, EventState.INTERESTED);
        assertEquals(user.getAttendanceList().size(), 1);
        AttendanceListEvent newEvent = Generator.generateAttendanceListEvent();
        em.persist(newEvent);
        sut.removeEvent(user.getAttendanceList(), newEvent);
        assertEquals(user.getAttendanceList().size(), 1);
    }

    @Test
    public void changeEventStateChangesEventState() {
        AttendanceListEvent event = Generator.generateAttendanceListEvent();
        em.persist(event);
        User user = Generator.generateStudent();
        em.persist(user);
        EventState oldState = EventState.INTERESTED;
        EventState newState = EventState.GOING;
        sut.addEvent(user.getAttendanceList(), event, oldState);
        assertEquals(user.getAttendanceList().size(), 1);
        User result = em.find(User.class, user.getId());
        assertEquals(result.getAttendanceList().getEvents().get(0).getState(), oldState);
        sut.changeEventState(user.getAttendanceList(), event, newState);
        assertEquals(result.getAttendanceList().getEvents().get(0).getState(), newState);
    }
*/

}

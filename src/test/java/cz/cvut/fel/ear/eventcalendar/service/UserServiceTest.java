package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.environment.Generator;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.Role;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

    @Mock
    UserService sut;
    @Mock
    InvitationService invitationService;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void createInvitatioCreatesInvitation() {
        User userFrom = Generator.generateStudent();
        User userTo = Generator.generateStudent();
        Event event = Generator.generateEvent();
        em.persist(userTo);
        em.persist(userFrom);
        em.persist(event);
        assertEquals(invitationService.findAll().size(), 0);
        sut.sendInvitation(userFrom, userTo, event);
        assertEquals(invitationService.findAll().size(), 1);
    }

    @Test
    public void deleteInvitatioDeletesInvitation() {
        User userFrom = Generator.generateStudent();
        User userTo = Generator.generateStudent();
        Event event = Generator.generateEvent();
        em.persist(userTo);
        em.persist(userFrom);
        em.persist(event);
        sut.sendInvitation(userFrom, userTo, event);
        assertEquals(invitationService.findAll().size(), 1);
        sut.deleteInvitation(userFrom, userTo, event);
        assertEquals(invitationService.findAll().size(), 0);

    }


    @Test
    public void createEventCreatesEvent() {
        User user = Generator.generateStudent();
        em.persist(user);
        sut.createEvent(user, "EVENT1", "Dejvice", new Date(), new Date(), 100);
        Event result = em.find(Event.class, 100);
        assertEquals(result.getName(), "EVENT1");
    }

    @Test
    public void changeRoleChangesRole() {
        User admin = Generator.generateAdmin();
        em.persist(admin);
        User user = Generator.generateUser();

        em.persist(user);
        sut.changeRoleToStudent(admin, user);
        em.merge(user);
        User result = em.find(User.class, user.getId());
        assertEquals(result.getRole(), Role.STUDENT);
    }

    @Test
    public void changeRoleChangesRoleFailedNotAdmin() {
        User student = Generator.generateStudent();
        em.persist(student);
        User user = Generator.generateUser();

        em.persist(user);
        sut.changeRoleToStudent(student, user);
        em.merge(user);
        User result = em.find(User.class, user.getId());
        assertEquals(result.getRole(), Role.USER);
    }
}

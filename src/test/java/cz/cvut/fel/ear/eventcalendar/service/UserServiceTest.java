package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.environment.Generator;
import cz.cvut.fel.ear.eventcalendar.model.Event;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTest {

    @Autowired
    UserService sut;
    @Autowired
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
        assertEquals(invitationService.findAll(), null);
        sut.sendInvitation(userFrom, userTo, event);
        assertEquals(invitationService.findAll().size(), 1);
    }
}

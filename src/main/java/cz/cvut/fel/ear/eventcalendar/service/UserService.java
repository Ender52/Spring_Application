package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.EventDao;
import cz.cvut.fel.ear.eventcalendar.dao.InvitationDao;
import cz.cvut.fel.ear.eventcalendar.dao.UserDao;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.Invitation;
import cz.cvut.fel.ear.eventcalendar.model.Role;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserDao dao;
    private final InvitationDao invitationDao;
    private final EventDao eventDao;

    @Autowired
    public UserService(UserDao dao, InvitationDao invitationDao, EventDao eventDao) {
        this.dao = dao;
        this.invitationDao = invitationDao;
        this.eventDao = eventDao;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public User find(Integer id) {
        return dao.find(id);
    }

    @Transactional
    public void persist(User user) {
        Objects.requireNonNull(user);
        dao.persist(user);
    }

    @Transactional
    public void update(User user) {
        dao.update(user);
    }

    @Transactional
    public void remove(User user) {
        dao.remove(user);
    }

    @Transactional
    public void sendInvitation(User userFrom, User userTo, Event event) {
        Objects.requireNonNull(userFrom);
        Objects.requireNonNull(userTo);
        Objects.requireNonNull(event);
        Invitation invitation = new Invitation();
        invitation.setFromUser(userFrom);
        invitation.setToUser(userTo);
        invitation.setEvent(event);

        invitationDao.persist(invitation);
    }

    @Transactional
    public void cancelInvitation(Invitation invitation) {
        Objects.requireNonNull(invitation);
        invitationDao.remove(invitationDao.find(invitation.getId()));
    }

    @Transactional
    public void createEvent(User creator, String name, String location, String dateFrom, String dateTo) {
        Objects.requireNonNull(creator);
        if (creator.getRole() != Role.STUDENT && creator.getRole() != Role.ADMIN) {
            return;
        }
        Objects.requireNonNull(name);
        Objects.requireNonNull(location);
        Objects.requireNonNull(dateFrom);
        Objects.requireNonNull(dateTo);
        Event event = new Event();
        event.setMadeByUser(creator);
        event.setName(name);
        event.setLocation(location);
        event.setDateFrom(dateFrom);
        event.setDateTo(dateTo);

        eventDao.persist(event);
    }

    @Transactional
    public void changeRoleToStudent(User admin, User user) {
        Objects.requireNonNull(admin);
        Objects.requireNonNull(user);
        admin.changeRoleToStudent(user);
    }

    @Transactional(readOnly = true)
    public boolean exists(String username) {
        return dao.findByUsername(username) != null;
    }

    @Transactional(readOnly = true)
    public List<User> findByFirstName(String firstName) {
        return dao.findByFirstName(firstName);
    }

    @Transactional(readOnly = true)
    public List<User> findByLastName(String lastName) {
        return dao.findByLastName(lastName);
    }
}

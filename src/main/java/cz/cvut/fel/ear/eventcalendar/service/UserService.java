package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.UserDao;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
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
        userFrom.sendInvitation(userTo, event);
    }
}

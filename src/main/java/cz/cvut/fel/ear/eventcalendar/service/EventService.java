package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.EventDao;
import cz.cvut.fel.ear.eventcalendar.dao.UserDao;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {
    private final EventDao dao;
    private final UserDao userDao;

    @Autowired
    public EventService(EventDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Event> findAll(Category category) {
        return dao.findAll(category);
    }

    @Transactional(readOnly = true)
    public List<Event> findByLocation(String location) {
        return dao.findByLocation(location);
    }

    @Transactional(readOnly = true)
    public List<Event> findMadeByUser(User user) {
        return dao.findMadeByUser(user);
    }

    @Transactional(readOnly = true)
    public Event find(Integer id) {
        return dao.find(id);
    }

    @Transactional
    public void persist(Event event) {
        Objects.requireNonNull(event);
        dao.persist(event);
    }

    @Transactional
    public void update(Event event) {
        dao.update(event);
    }

    @Transactional
    public void remove(Event event) {
        dao.remove(event);
    }

    @Transactional
    public List<User> getAttendees(Event event) {
        List<User> users = userDao.findAll();
        List<User> attendees = new ArrayList<>();
        for (User u : users) {
            for (AttendanceListEvent e : u.getAttendanceList()){
                if (e.getAleId().getEvent() == event){
                    attendees.add(u);
                    break;
                }
            }
        }
        return attendees;
    }
}

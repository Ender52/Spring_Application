package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.EventDao;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {
    private final EventDao dao;

    @Autowired
    public EventService(EventDao dao){
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Event find(Integer id){
        return dao.find(id);
    }

    @Transactional
    public void persist(Event event){
        Objects.requireNonNull(event);
        dao.persist(event);
    }

    @Transactional
    public void update(Event event){
        dao.update(event);
    }

    @Transactional
    public void remove(Event event){
        dao.remove(event);
    }
}

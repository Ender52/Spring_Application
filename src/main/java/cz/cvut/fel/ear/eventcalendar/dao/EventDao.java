package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

@Repository
public class EventDao {
    @PersistenceContext
    private EntityManager em;

    public Event find(Integer id) {
        Objects.requireNonNull(id);
        return em.find(Event.class, id);
    }
}

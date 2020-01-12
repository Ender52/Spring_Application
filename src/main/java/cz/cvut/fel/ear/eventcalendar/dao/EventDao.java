package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

@Repository
public class EventDao extends BaseDao<Event> {
    public EventDao() {
        super(Event.class);
    }

    public List<Event> findAll(Category category) {
        Objects.requireNonNull(category);
        try {
            return em.createNamedQuery("Event.findByCategory", Event.class).setParameter("category", category).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Event> findByLocation(String location) {
        try {
            return em.createNamedQuery("Event.findByLocation", Event.class).setParameter("location", location).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Event> findMadeByUser(User madeByUser) {
        Objects.requireNonNull(madeByUser);
        try {
            return em.createNamedQuery("Event.findMadeByUser", Event.class).setParameter("madeByUser", madeByUser).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

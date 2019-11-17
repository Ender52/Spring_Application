package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao extends BaseDao<Event>{
    public EventDao(){
        super(Event.class);
    }
}

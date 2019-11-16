package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Event;

public class EventDao extends BaseDao<Event> {
    protected EventDao(Class<Event> type) {
        super(type);
    }
}

package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.AttendanceListEventDao;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import cz.cvut.fel.ear.eventcalendar.model.EventState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AttendanceListEventService {
    private final AttendanceListEventDao dao;

    @Autowired
    public AttendanceListEventService(AttendanceListEventDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void changeEventState(AttendanceListEvent event, EventState newState) {
        Objects.requireNonNull(newState);
        event.changeState(newState);
        dao.update(event);
    }
}

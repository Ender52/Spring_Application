package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.AttendanceListDao;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceList;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import cz.cvut.fel.ear.eventcalendar.model.EventState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AttendanceListService {
    private final AttendanceListDao dao;

    @Autowired
    public AttendanceListService(AttendanceListDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addEvent(AttendanceList attendanceList, AttendanceListEvent toAdd, EventState stateToAdd) {
        Objects.requireNonNull(attendanceList);
        Objects.requireNonNull(toAdd);
        Objects.requireNonNull(stateToAdd);
        attendanceList.addEvent(toAdd, stateToAdd);
        dao.update(attendanceList);
    }

    @Transactional
    public void removeEvent(AttendanceList attendanceList, AttendanceListEvent toRemove) {
        Objects.requireNonNull(attendanceList);
        Objects.requireNonNull(toRemove);
        attendanceList.removeEvent(toRemove);
        dao.update(attendanceList);
    }

    @Transactional
    public void changeEventState(AttendanceList attendanceList, AttendanceListEvent event, EventState newState) {
        Objects.requireNonNull(attendanceList);
        Objects.requireNonNull(event);
        Objects.requireNonNull(newState);
        attendanceList.changeEventState(event, newState);
        dao.update(attendanceList);
    }
}

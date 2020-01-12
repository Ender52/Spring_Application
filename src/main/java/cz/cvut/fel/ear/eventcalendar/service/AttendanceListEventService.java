package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.AttendanceListEventDao;
import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import cz.cvut.fel.ear.eventcalendar.model.EventState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AttendanceListEventService {
    private final AttendanceListEventDao dao;

    @Autowired
    public AttendanceListEventService(AttendanceListEventDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<AttendanceListEvent> findAll(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public AttendanceListEvent find(Integer id){
        return dao.find(id);
    }

    @Transactional
    public void persist(AttendanceListEvent attendanceListEvent){
        Objects.requireNonNull(attendanceListEvent);
        dao.persist(attendanceListEvent);
    }

    @Transactional
    public void update(AttendanceListEvent attendanceListEvent){
        dao.update(attendanceListEvent);
    }

    @Transactional
    public void remove(AttendanceListEvent atendanceListEvent){
        dao.remove(atendanceListEvent);
    }

    @Transactional
    public void changeState(AttendanceListEvent event, EventState newState) {
        Objects.requireNonNull(newState);
        event.changeState(newState);
        dao.update(event);
    }
}

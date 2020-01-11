package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.AttendanceListEvent;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceListEventDao extends BaseDao<AttendanceListEvent> {
    public AttendanceListEventDao(){
        super(AttendanceListEvent.class);
    }
}

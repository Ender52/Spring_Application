package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.AttendanceList;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceListDao extends BaseDao<AttendanceList> {
    public AttendanceListDao() {
        super(AttendanceList.class);
    }
}

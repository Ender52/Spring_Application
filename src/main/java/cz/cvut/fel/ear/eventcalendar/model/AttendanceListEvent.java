package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ATTENDANCELIST")
public class AttendanceListEvent extends Event {
}

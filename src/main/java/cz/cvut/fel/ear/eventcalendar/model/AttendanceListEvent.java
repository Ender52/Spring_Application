package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("ATTENDANCELIST")
public class AttendanceListEvent extends Event {

    public AttendanceListEvent() {
    }

    public AttendanceListEvent(Event other) {
        setName(other.getName());
        setLocation(other.getLocation());
        setDateFrom(other.getDateFrom());
        setDateTo(other.getDateTo());
        setAttendees(other.getAttendees());
    }

    @Enumerated(EnumType.STRING)
    private EventState state;

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AttendanceListEvent{" +
                this.getName() + " " + this.getLocation() +
                "(" + this.getDateFrom() + " - " + this.getDateTo() + ")}";
    }
}

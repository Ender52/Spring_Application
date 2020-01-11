package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("ATTENDANCELIST")
public class AttendanceListEvent extends Event {

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @OneToOne
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @Enumerated(value = EnumType.STRING)
    private EventState state;

    public AttendanceListEvent() {
    }

    public AttendanceListEvent(Event other) {
        setName(other.getName());
        setLocation(other.getLocation());
        setDateFrom(other.getDateFrom());
        setDateTo(other.getDateTo());
        setMadeByUser(other.getMadeByUser());
        setCategories(other.getCategories());
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public void changeState(EventState state){
        Objects.requireNonNull(state);
        this.setState(state);
    }

    @Override
    public String toString() {
        return "AttendanceListEvent{" +
                this.getName() + " " + this.getLocation() +
                "(" + this.getDateFrom() + " - " + this.getDateTo() + ")}";
    }
}

package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AttendanceListEvent extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    @Enumerated(value = EnumType.STRING)
    private EventState state;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public void changeState(EventState state) {
        Objects.requireNonNull(state);
        this.setState(state);
    }

    @Override
    public String toString() {
        return "AttendanceListEvent{" +
                this.getOwner() + " " + this.getEvent() + "}";
    }
}

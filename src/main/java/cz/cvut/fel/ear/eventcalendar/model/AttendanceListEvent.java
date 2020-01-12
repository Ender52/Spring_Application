package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AttendanceListEvent {

    @Id
    private AttendanceListEventId aleId;

    @Enumerated(value = EnumType.STRING)
    private EventState state;

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public AttendanceListEventId getAleId() {
        return aleId;
    }

    public void setAleId(AttendanceListEventId aleId) {
        this.aleId = aleId;
    }

    public void changeState(EventState state) {
        Objects.requireNonNull(state);
        this.setState(state);
    }

    @Override
    public String toString() {
        return "AttendanceListEvent{" +
                this.getAleId().getOwner() + " " + this.getAleId().getEvent() + "}";
    }
}

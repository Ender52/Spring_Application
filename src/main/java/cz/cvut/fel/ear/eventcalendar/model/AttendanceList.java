package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.*;

@Entity
public class AttendanceList extends AbstractEntity {
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<AttendanceListEvent> events = new ArrayList<>();

    public List<AttendanceListEvent> getEvents() {
        return events;
    }

    public void setEvents(List<AttendanceListEvent> events) {
        this.events = events;
    }

    public void addEvent(AttendanceListEvent event, EventState state) {
        Objects.requireNonNull(event);
        event.setState(state);
        if (events == null) {
            this.events = new ArrayList<>();
        }
        final Optional<AttendanceListEvent> existing = events.stream().filter(ev -> ev.getId().equals(event.getId())).findAny();
        if (!existing.isPresent()) {
            events.add(event);
        }
    }

    public void removeEvent(Event event) {
        Objects.requireNonNull(event);
        final Iterator<AttendanceListEvent> it = events.iterator();
        while (it.hasNext()) {
            final AttendanceListEvent cur = it.next();
            if (cur.getId().equals(event.getId())) {
                it.remove();
                break;
            }
        }
    }

    public void changeEventState(AttendanceListEvent event, EventState newState) {
        Objects.requireNonNull(event);
        final Iterator<AttendanceListEvent> it = events.iterator();
        while (it.hasNext()) {
            final AttendanceListEvent cur = it.next();
            if (cur.getId().equals(event.getId())) {
                cur.setState(newState);
                break;
            }
        }
    }
    public int size() {
        return events.size();
    }
}

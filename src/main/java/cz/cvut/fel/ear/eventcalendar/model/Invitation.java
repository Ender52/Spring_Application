package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;

@Entity
public class Invitation extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private User fromUser;

    @Basic(optional = false)
    @Column(nullable = false)
    private User toUser;

    @OneToOne
    private Event event;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Invitation{from " +
                fromUser + " to " + toUser +
                "(" + event + ")}";
    }
}

package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;

@Entity
public class Invitation extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser;

    @OneToOne
    @JoinColumn(name = "TO_USER_ID")
    private User toUser;

    @OneToOne
    @JoinColumn(name = "EVENT_ID")
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

package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends User {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private Integer studentId;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> friendList = new ArrayList<>();

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    public void createEvent(String name, String location, Date dateFrom, Date dateTo, List<User> attendees){
        Event event = new Event();
        event.setName(name);
        event.setLocation(location);
        event.setDateFrom(dateFrom);
        event.setDateTo(dateTo);
        event.setAttendees(attendees);
    }

    @Override
    public String toString() {
        return "User{" +
                this.getFirstName() + " " + this.getLastName() +
                "(" + this.getUsername() + ", studentId = " + studentId + ")}";
    }
}

package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;

@Entity
@Table(name = "CALENDAR_USER")
public abstract class User extends AbstractEntity {

    @Basic(optional = true)
    @Column(nullable = true)
    private String firstName;

    @Basic(optional = true)
    @Column(nullable = true)
    private String lastName;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String username;

    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private AttendanceList attendanceList;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(AttendanceList attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void sendInvitation(User toUser, Event event){
        Invitation invitation = new Invitation();
        invitation.setEvent(event);
        invitation.setFromUser(this);
        invitation.setToUser(toUser);
    }

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + username + ")}";
    }
}

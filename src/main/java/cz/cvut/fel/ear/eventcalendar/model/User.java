package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CALENDAR_USER")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User extends AbstractEntity {

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

    @Enumerated(value=EnumType.STRING)
    private Role role;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<AttendanceListEvent> attendanceList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> friendList = new ArrayList<>();

    public User() {
        this.role = Role.USER;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
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

    public List<AttendanceListEvent> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<AttendanceListEvent> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public void addUserToFriendList(User user){
        Objects.requireNonNull(user);
        if (friendList == null) {
            this.friendList = new ArrayList<>();
        }
        friendList.add(user);
    }

    public void removeUserFromFriendList(User user){
        Objects.requireNonNull(user);
        if (friendList == null) {
            return;
        }
        friendList.removeIf(c -> Objects.equals(c.getId(), user.getId()));
    }

    public void addEventToAttendanceList(AttendanceListEvent event, EventState state){
        Objects.requireNonNull(event);
        if (attendanceList == null) {
            this.attendanceList = new ArrayList<>();
        }
        event.setState(state);
        attendanceList.add(event);
    }

    public void removeEventFromAttendanceList(AttendanceListEvent event){
        Objects.requireNonNull(event);
        if (attendanceList == null) {
            return;
        }
        attendanceList.removeIf(c -> Objects.equals(c.getId(), event.getId()));
    }

    public void changeRoleToStudent(User user){
        if (this.role == Role.ADMIN) {
            user.setRole(Role.STUDENT);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + username + ")}";
    }
}

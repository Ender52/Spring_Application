package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends User {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private Integer studentId;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<User> friendList = new ArrayList<>();

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "User{" +
                this.getFirstName() + " " + this.getLastName() +
                "(" + this.getUsername() + ", studentId = " + studentId + ")}";
    }
}

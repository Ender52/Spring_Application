package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends User {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private Integer studentId;
}

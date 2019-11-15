package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String name;
}

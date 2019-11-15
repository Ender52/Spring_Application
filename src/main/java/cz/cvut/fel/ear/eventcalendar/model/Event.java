package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Event extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    private String location;

    @Basic(optional = false)
    @Column(nullable = false)
    private Date dateFrom;

    @Basic(optional = false)
    @Column(nullable = false)
    private Date dateTo;

}

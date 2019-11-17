package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Basic(optional = false)
    @Column(nullable = false)
    private Student madeByStudent;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<User> attendees = new ArrayList<>();

    @ManyToMany
    @OrderBy("name")
    private List<Category> categories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Student getMadeByStudent() {
        return madeByStudent;
    }

    public void setMadeByStudent(Student madeByStudent) {
        this.madeByStudent = madeByStudent;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<User> attendees) {
        this.attendees = attendees;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        Objects.requireNonNull(category);
        if (categories == null) {
            this.categories = new ArrayList<>();
        }
        categories.add(category);
    }

    public void removeCategory(Category category) {
        Objects.requireNonNull(category);
        if (categories == null) {
            return;
        }
        categories.removeIf(c -> Objects.equals(c.getId(), category.getId()));
    }

    @Override
    public String toString() {
        return "Event{" +
                name + " " + location +
                "(" + dateFrom + " - " + dateTo + ")}";
    }

}

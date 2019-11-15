package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;

@Entity
@Table(name = "CALENDAR_USER")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
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

    @Override
    public String toString() {
        return "User{" +
                firstName + " " + lastName +
                "(" + username + ")}";
    }
}

package cz.cvut.fel.ear.eventcalendar.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
})
public class Category extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                "}";
    }
}

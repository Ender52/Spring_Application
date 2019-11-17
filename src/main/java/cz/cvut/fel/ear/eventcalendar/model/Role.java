package cz.cvut.fel.ear.eventcalendar.model;

public enum Role {
    USER("ROLE_USER"), STUDENT("ROLE_STUDENT"), ADMIN("ROLE_ADMIN");

    private final String name;

    Role(String name) {this.name = name;}

    @Override
    public String toString() {return name;}
}

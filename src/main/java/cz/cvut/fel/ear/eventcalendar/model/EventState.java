package cz.cvut.fel.ear.eventcalendar.model;

public enum EventState {
    GOING("STATE_GOING"), INTERESTED("STATE_INTERESTED"), NOTINTERESTED("STATE_NOTINTERESTED");

    private final String name;

    EventState(String name) {this.name = name;}

    @Override
    public String toString() {return name;}
}

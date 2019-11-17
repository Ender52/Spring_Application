package cz.cvut.fel.ear.eventcalendar.environment;

import cz.cvut.fel.ear.eventcalendar.model.Event;

import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    public static Event generateEvent() {
        final Event event = new Event();
        return event;
    }
}

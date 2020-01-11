package cz.cvut.fel.ear.eventcalendar.environment;

import cz.cvut.fel.ear.eventcalendar.model.*;

import java.util.Date;
import java.util.Random;

public class Generator {

    private static final Random RAND = new Random();

    public static int randomInt() {
        return RAND.nextInt();
    }

    public static boolean randomBoolean() {
        return RAND.nextBoolean();
    }

    public static User generateUser() {
        final User user = new User();
        user.setRole(Role.USER);
        user.setFirstName("FirstName" + randomInt());
        user.setLastName("LastName" + randomInt());
        user.setUsername("username" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        return user;
    }

    public static User generateStudent() {
        final User user = new User();
        user.setRole(Role.STUDENT);
        user.setFirstName("FirstName" + randomInt());
        user.setLastName("LastName" + randomInt());
        user.setUsername("username" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        return user;
    }

    public static User generateAdmin() {
        final User user = new User();
        user.setRole(Role.ADMIN);
        user.setFirstName("FirstName" + randomInt());
        user.setLastName("LastName" + randomInt());
        user.setUsername("username" + randomInt() + "@kbss.felk.cvut.cz");
        user.setPassword(Integer.toString(randomInt()));
        return user;
    }

    public static Category generateCategory() {
        final Category category = new Category();
        category.setName("Category" + randomInt());
        return category;
    }

    public static Event generateEvent() {
        final Event event = new Event();
        event.setName("Event" + randomInt());
        event.setLocation("");
        event.setDateFrom(new Date());
        event.setDateTo(new Date());
        User creator = generateStudent();
        event.setMadeByUser(creator);
        return event;
    }

    public static AttendanceListEvent generateAttendanceListEvent() {
        AttendanceListEvent attendanceListEvent = new AttendanceListEvent();
        attendanceListEvent.setEvent(generateEvent());
        attendanceListEvent.setOwner(generateStudent());
        attendanceListEvent.setState(EventState.GOING);
        return attendanceListEvent;
    }

}

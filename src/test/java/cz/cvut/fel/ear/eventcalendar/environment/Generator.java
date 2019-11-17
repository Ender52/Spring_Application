package cz.cvut.fel.ear.eventcalendar.environment;

import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.Role;
import cz.cvut.fel.ear.eventcalendar.model.User;

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

    public static User generateStudent() {
        final User user = new User();
        user.setRole(Role.STUDENT);
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
        User cretor = generateStudent();
        return cretor.createEvent("Event" + randomInt(), "", new Date(), new Date());
    }
}

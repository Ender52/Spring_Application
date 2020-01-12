package cz.cvut.fel.ear.eventcalendar.exceptions;

/**
 * Signifies that invalid data have been provided to the application.
 */
public class ValidationException extends CalendarException {

    public ValidationException(String message) {
        super(message);
    }
}

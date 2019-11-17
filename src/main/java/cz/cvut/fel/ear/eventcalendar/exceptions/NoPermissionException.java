package cz.cvut.fel.ear.eventcalendar.exceptions;

public class NoPermissionException extends CalendarException {
    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}

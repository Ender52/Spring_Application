package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.exceptions.NotFoundException;
import cz.cvut.fel.ear.eventcalendar.exceptions.ValidationException;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.service.EventService;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/events")
public class EventController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    private final EventService service;
    private final UserService userService;

    public EventController(EventService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEvents() {
        return service.findAll();
    }

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Void> createEvent(@RequestBody Event event){
       service.persist(event);
       LOG.debug("Created event {}.", event);
       final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", event.getId());
       return new ResponseEntity<>(headers, HttpStatus.CREATED);
   }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEvent(@PathVariable Integer id) {
        final Event e = service.find(id);
        if (e == null) {
            throw NotFoundException.create("Event", id);
        }
        return e;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable Integer id, @RequestBody Event event) {
        final Event original = getEvent(id);
        if (!original.getId().equals(event.getId())) {
            throw new ValidationException("Event identifier in the data does not match the one in the request URL.");
        }
        service.update(event);
        LOG.debug("Updated event {}.", event);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEvent(@PathVariable Integer id) {
        final Event toRemove = service.find(id);
        if (toRemove == null) {
            return;
        }
        service.remove(toRemove);
        LOG.debug("Removed event {}.", toRemove);
    }
}

package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.User;
import cz.cvut.fel.ear.eventcalendar.service.EventService;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/events")
public class EventController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    private final UserService userService;
    private final EventService eventService;

    public EventController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @PostMapping(value = "/{name}/{location}/{dateFrom}/{dateTo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody User creator, @PathVariable String name, @PathVariable String location, @PathVariable Date dateFrom, @PathVariable Date dateTo) {
        userService.createEvent(creator, name, location, dateFrom, dateTo);
//        LOG.debug("Created product {}.", event);
//        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", event.getId());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getCategories() {
        return eventService.findAll();
    }


//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test(@RequestBody int nom) {
//        return "eventService.findAll();";
//    }
//
//
//        productService.persist(product);
//        LOG.debug("Created product {}.", product);
//        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", product.getId());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
}

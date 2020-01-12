package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.exceptions.NotFoundException;
import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.service.CategoryService;
import cz.cvut.fel.ear.eventcalendar.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService service;

    private final EventService eventService;

    @Autowired
    public CategoryController(CategoryService service, EventService eventService) {
        this.service = service;
        this.eventService = eventService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategories() {
        return service.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        service.persist(category);
        LOG.debug("Created category {}.", category);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", category.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getById(@PathVariable Integer id) {
        final Category category = service.find(id);
        if (category == null) {
            throw NotFoundException.create("Category", id);
        }
        return category;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getEventsByCategory(@PathVariable Integer id) {
        return eventService.findAll(getById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    @PostMapping(value = "/{id}/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEventToCategory(@PathVariable Integer id, @RequestBody Event event) {
        final Category category = getById(id);
        service.addEvent(category, event);
        LOG.debug("Event {} added into category {}.", event, category);
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    @PostMapping(value = "/{categoryId}/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEventToCategory(@PathVariable Integer categoryId, @PathVariable Integer eventId) {
        final Category category = getById(categoryId);
        final Event event = eventService.find(eventId);
        service.addEvent(category, event);
        LOG.debug("Event {} added into category {}.", event, category);
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    @DeleteMapping(value = "/{categoryId}/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEventFromCategory(@PathVariable Integer categoryId,
                                          @PathVariable Integer eventId) {
        final Category category = getById(categoryId);
        final Event toRemove = eventService.find(eventId);
        if (toRemove == null) {
            throw NotFoundException.create("Event", eventId);
        }
        service.removeEvent(category, toRemove);
        LOG.debug("Event {} removed from category {}.", toRemove, category);
    }
}

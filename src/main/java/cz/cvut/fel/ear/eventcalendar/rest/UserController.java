package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.exceptions.NotFoundException;
import cz.cvut.fel.ear.eventcalendar.model.User;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        service.persist(user);
        LOG.debug("User {} successfully registered.", user);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable Integer id) {
        final User u = service.find(id);
        if (u == null) {
            throw NotFoundException.create("Product", id);
        }
        return u;
    }
}


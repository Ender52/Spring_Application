package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.exceptions.NotFoundException;
import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import cz.cvut.fel.ear.eventcalendar.model.Invitation;
import cz.cvut.fel.ear.eventcalendar.model.User;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.security.model.AuthenticationToken;
import cz.cvut.fel.ear.eventcalendar.service.InvitationService;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserController {

    private final UserService service;

    private final InvitationService invitationService;

    @Autowired
    public UserController(UserService service, InvitationService invitationService) {
        this.service = service;
        this.invitationService = invitationService;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST')")
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getCurrent(Principal principal) {
        final AuthenticationToken auth = (AuthenticationToken) principal;
        return auth.getPrincipal().getUser();
    }

    @PostMapping(value = "/invitation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createInvitation(@RequestBody Invitation invitation) {
        invitationService.persist(invitation);
        LOG.debug("Created invitation {}.", invitation);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", invitation.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}


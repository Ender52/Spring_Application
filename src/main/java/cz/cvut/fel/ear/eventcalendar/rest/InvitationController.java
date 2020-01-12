package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.exceptions.NotFoundException;
import cz.cvut.fel.ear.eventcalendar.model.Invitation;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.service.InvitationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/invitations")
public class InvitationController {
    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    private final InvitationService service;

    public InvitationController(InvitationService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Invitation> getInvitations() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createInvitation(@RequestBody Invitation invitation){
        service.persist(invitation);
        LOG.debug("Created invitation {}.", invitation);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", invitation.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Invitation getInvitation(@PathVariable Integer id) {
        final Invitation i = service.find(id);
        if (i == null) {
            throw NotFoundException.create("Invitation", id);
        }
        return i;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeInvitation(@PathVariable Integer id) {
        final Invitation toRemove = service.find(id);
        if (toRemove == null) {
            return;
        }
        service.remove(toRemove);
        LOG.debug("Removed invitation {}.", toRemove);
    }
}

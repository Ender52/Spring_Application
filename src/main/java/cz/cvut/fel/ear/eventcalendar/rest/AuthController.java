package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.security.DefaultAuthenticationProvider;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    private final UserService userService;

    private final DefaultAuthenticationProvider provider;

    @Autowired
    public AuthController(UserService userService, DefaultAuthenticationProvider provider) {
        this.userService = userService;
        this.provider = provider;
    }

    @PostMapping(consumes = MediaType.ALL_VALUE, value = "/login")
    public ResponseEntity<Void> login(@RequestParam String username, @RequestParam String password) {
        if (userService.exists(username)) {
            final Authentication auth = new UsernamePasswordAuthenticationToken(username, password);

            provider.authenticate(auth);

            final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}

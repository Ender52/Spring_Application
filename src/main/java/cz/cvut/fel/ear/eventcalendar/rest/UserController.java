package cz.cvut.fel.ear.eventcalendar.rest;

import cz.cvut.fel.ear.eventcalendar.model.User;
import cz.cvut.fel.ear.eventcalendar.rest.util.RestUtils;
import cz.cvut.fel.ear.eventcalendar.service.UserService;
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


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    //private final UserService userService;

    //@Autowired
    /*public UserController(UserService userService) {
        this.userService = userService;
    }*/

    @RequestMapping("/hello")
    public String foo(){
        return "xd";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.persist(user);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getCategories() {
        return userService.findAll();
    }
}


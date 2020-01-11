package cz.cvut.fel.ear.eventcalendar.rest;

import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/rest/users")
public class UserController {

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
}


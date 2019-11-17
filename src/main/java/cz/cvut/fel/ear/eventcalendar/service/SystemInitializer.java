package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.model.Role;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Component
public class SystemInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(SystemInitializer.class);

    /**
     * Default username
     */
    private static final String USERNAME = "student@fel.cvut.cz";

    private final UserService userService;

    private final PlatformTransactionManager txManager;

    @Autowired
    public SystemInitializer(UserService userService,
                             PlatformTransactionManager txManager) {
        this.userService = userService;
        this.txManager = txManager;
    }

    @PostConstruct
    private void initSystem() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);
        txTemplate.execute((status) -> {
            generateUser();
            return null;
        });
    }

    /**
     * Generates a user account if it does not already exist.
     */
    private void generateUser() {
        if (userService.exists(USERNAME)) {
            return;
        }
        final User user = new User();
        user.setUsername(USERNAME);
        user.setFirstName("Alik");
        user.setLastName("Novak");
        user.setPassword("qwerty");
        user.setRole(Role.ADMIN);
        LOG.info("Generated admin user with credentials " + user.getUsername() + "/" + user.getPassword());
        userService.persist(user);
    }
}

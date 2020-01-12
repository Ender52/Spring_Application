package cz.cvut.fel.ear.eventcalendar.service.security;

import cz.cvut.fel.ear.eventcalendar.dao.UserDao;
import cz.cvut.fel.ear.eventcalendar.model.User;
import cz.cvut.fel.ear.eventcalendar.security.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsService(UserDao userDao) {this.userDao = userDao;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.findByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }
        return new cz.cvut.fel.ear.eventcalendar.security.model.UserDetails(user);
    }
}

package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User>{
    public UserDao() {
        super(User.class);
    }
}

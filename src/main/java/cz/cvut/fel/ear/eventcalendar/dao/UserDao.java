package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDao extends BaseDao<User> {
    public UserDao() {
        super(User.class);
    }

    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> findByFirstName(String firstName) {
        try {
            return em.createNamedQuery("User.findByFirstName", User.class).setParameter("firstName", firstName)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> findByLastName(String lastName) {
        try {
            return em.createNamedQuery("User.findByLastName", User.class).setParameter("lastName", lastName)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

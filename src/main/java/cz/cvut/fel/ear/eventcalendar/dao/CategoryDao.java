package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CategoryDao extends BaseDao<Category> {
    public CategoryDao() {
        super(Category.class);
    }

    public List<Category> findByName(String name) {
        try {
            return em.createNamedQuery("Category.findByName", Category.class).setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

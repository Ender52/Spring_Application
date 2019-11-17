package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.CategoryDao;
import cz.cvut.fel.ear.eventcalendar.dao.EventDao;
import cz.cvut.fel.ear.eventcalendar.model.Category;
import cz.cvut.fel.ear.eventcalendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryDao dao;
    private final EventDao eventDao;

    @Autowired
    public CategoryService(CategoryDao dao, EventDao eventDao){
        this.dao = dao;
        this.eventDao = eventDao;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Category find(Integer id){
        return dao.find(id);
    }

    @Transactional
    public void persist(Category category){
        Objects.requireNonNull(category);
        dao.persist(category);
    }

    @Transactional
    public void update(Category category){
        dao.update(category);
    }

    @Transactional
    public void remove(Category category){
        dao.remove(category);
    }

    @Transactional
    public void addEvent(Category category, Event event){
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.addCategory(category);
        eventDao.update(event);
    }

    @Transactional
    public void removeEvent(Category category, Event event){
        Objects.requireNonNull(category);
        Objects.requireNonNull(event);
        event.removeCategory(category);
        eventDao.update(event);
    }

}

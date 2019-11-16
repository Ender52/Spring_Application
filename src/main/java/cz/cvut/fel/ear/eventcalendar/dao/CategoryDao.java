package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Category;

public class CategoryDao extends BaseDao<Category> {
    protected CategoryDao(Class<Category> type) {
        super(type);
    }
}

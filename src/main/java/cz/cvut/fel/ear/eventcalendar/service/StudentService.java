package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.UserDao;
import cz.cvut.fel.ear.eventcalendar.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
public class StudentService extends UserService{

    @Autowired
    public StudentService(UserDao dao){
        super(dao);
    }

    @Transactional
    public void createEvent(Student student, String name, String location, Date dateFrom, Date dateTo){
        Objects.requireNonNull(student);
        student.createEvent(name, location, dateFrom, dateTo);
    }
}

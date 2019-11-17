package cz.cvut.fel.ear.eventcalendar.service;

import cz.cvut.fel.ear.eventcalendar.dao.InvitationDao;
import cz.cvut.fel.ear.eventcalendar.model.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class InvitationService {
    private final InvitationDao dao;

    @Autowired
    public InvitationService(InvitationDao dao){
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public List<Invitation> findAll(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Invitation find(Integer id){
        return dao.find(id);
    }

    @Transactional
    public void persist(Invitation invitation){
        Objects.requireNonNull(invitation);
        dao.persist(invitation);
    }

    @Transactional
    public void update(Invitation invitation){
        dao.update(invitation);
    }

    @Transactional
    public void remove(Invitation invitation){
        dao.remove(invitation);
    }
}

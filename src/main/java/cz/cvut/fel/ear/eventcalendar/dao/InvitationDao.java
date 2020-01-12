package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Invitation;
import org.springframework.stereotype.Repository;

@Repository
public class InvitationDao extends BaseDao<Invitation> {
    public InvitationDao() {
        super(Invitation.class);
    }
}

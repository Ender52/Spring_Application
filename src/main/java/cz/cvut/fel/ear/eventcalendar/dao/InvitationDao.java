package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.model.Invitation;

public class InvitationDao extends BaseDao<Invitation> {
    protected InvitationDao(Class<Invitation> type) {
        super(type);
    }
}

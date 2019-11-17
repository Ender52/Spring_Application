package cz.cvut.fel.ear.eventcalendar.dao;

import cz.cvut.fel.ear.eventcalendar.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// DataJpaTest does not load all the application beans, it starts only persistence-related stuff
@DataJpaTest
public class DaoTest {

    @Autowired
    protected AttendanceListDao attendanceListDao;

    @Autowired
    protected CategoryDao categoryDao;

    @Autowired
    protected EventDao eventDao;

    @Autowired
    protected InvitationDao invitationDao;

    @Autowired
    protected UserDao userDao;

    /**
     * This test checks if the spring application contexts contains all the DAO beans from the cz.cvut.fel.ear.eventcalendar.dao  package
     */
    @Test
    public void testRepositoriesInApplicationContext(){
        Assert.assertNotNull(attendanceListDao);
        Assert.assertNotNull(categoryDao);
        Assert.assertNotNull(eventDao);
        Assert.assertNotNull(invitationDao);
        Assert.assertNotNull(userDao);
    }
}

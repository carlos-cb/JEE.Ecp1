package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoITest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DaosService daosService;
    
    @Autowired
    private TokenDao tokenDao;

    @Test
    public void testCreate() {
        assertTrue(userDao.count() >= 8);
    }

    @Test
    public void testFindDistinctByUsernameOrEmail() {
        User u1 = (User) daosService.getMap().get("u1");
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getUsername()));
        assertEquals(u1, userDao.findByUsernameOrEmail(u1.getEmail()));
        assertNull(userDao.findByUsernameOrEmail("kk"));
    }

    @Test
    public void testFindByTokenValue() {
        User u1 = (User) daosService.getMap().get("u1");
        Token t1 = (Token) daosService.getMap().get("tu1");
        assertEquals(u1, userDao.findByTokenValue(t1.getValue()));
        assertNull(userDao.findByTokenValue("kk"));
    }
    
    @Test
    public void testFindByTokenNotExpired(){
    	assertEquals(4, tokenDao.count());
    	Token token = new Token((User) daosService.getMap().get("u3"));
    	Calendar date = Calendar.getInstance();
    	date.add(Calendar.HOUR_OF_DAY, -1);
    	token.setExpirationDate(date);
    	tokenDao.save(token);
    	assertEquals(5, tokenDao.count());
    	assertNull(userDao.findByTokenNotExpired(token.getValue(), Calendar.getInstance()));
    	User u1 = (User) daosService.getMap().get("u1");
    	Token t1 = (Token) daosService.getMap().get("tu1");
    	assertEquals(u1, userDao.findByTokenNotExpired(t1.getValue(), Calendar.getInstance()));
    }
}

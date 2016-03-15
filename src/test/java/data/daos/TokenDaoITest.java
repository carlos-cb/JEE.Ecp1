package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u4");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
    
    @Test
    public void testDeleteExpiredToken(){
    	long numToken = tokenDao.count();
    	Token token = new Token((User) daosService.getMap().get("u5"));
    	Calendar date = Calendar.getInstance();
    	date.add(Calendar.HOUR_OF_DAY, -2);
    	token.setExpirationDate(date);
    	tokenDao.save(token);
    	assertEquals(numToken+1, tokenDao.count()); 
    	tokenDao.deleteExpiredToken(Calendar.getInstance());
    	assertEquals(numToken, tokenDao.count());
    }

}

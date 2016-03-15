package data.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenExpirationDate(){
    	User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
    	Token token = new Token(user);
    	Calendar date = Calendar.getInstance();
    	date.set(2000, 1, 1);
    	token.setExpirationDate(date);
    	assertTrue(token.tokenHasExpired());
    }
    
    @Test
    public void testTokenNotExpirationDate(){
    	User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
    	Token token = new Token(user);
    	Calendar date = Calendar.getInstance();
    	token.setExpirationDate(date);
    	assertFalse(token.tokenHasExpired());
    }
    
}

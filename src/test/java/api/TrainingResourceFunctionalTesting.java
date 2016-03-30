package api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import business.wrapper.TrainingWrapper;
import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingResourceFunctionalTesting {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TrainingDao trainingDao;
	
	private RestService restService = new RestService();
	

	@Test
	public void testCreateTraining(){
		assertEquals(0, trainingDao.count());
		User user = userDao.findByUsernameOrEmail("trainer");
		restService.createCourt("7");
		TrainingWrapper trainingWrapper = restService.createTrainingWrapper(user.getId(), 7);
		restService.createTraining(trainingWrapper);
		assertEquals(1, trainingDao.count());
	}
	
	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}


}

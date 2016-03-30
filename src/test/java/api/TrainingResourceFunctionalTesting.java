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
	
	@Test
	public void testDeleteTraining(){
		assertEquals(0, trainingDao.count());
		User user = userDao.findByUsernameOrEmail("trainer");
		restService.createCourt("8");
		TrainingWrapper trainingWrapper2 = restService.createTrainingWrapper(user.getId(), 8);
		restService.createTraining(trainingWrapper2);
		assertEquals(1, trainingDao.count());
		restService.deleteTraining(trainingDao.findByCourtId(8).getId());
		assertEquals(0, trainingDao.count());
		//assertEquals(1, trainingDao.findByCourtId(8).getId());
	}
	
	@Test
	public void testAddTrainingStudent(){
		User user = userDao.findByUsernameOrEmail("trainer");
		User player = new User("bei", "bei@gmail.com", "1", null);
		userDao.save(player);
		restService.createCourt("9");
		TrainingWrapper trainingWrapper3 = restService.createTrainingWrapper(user.getId(), 9);
		restService.createTraining(trainingWrapper3);
		assertEquals(1, trainingDao.count());
		assertEquals(2, trainingDao.findByCourtId(9).getId());
		restService.addStudent(trainingDao.findByCourtId(9).getId(), player.getId());
		assertTrue(trainingDao.findByCourtId(9).existStudent(player.getId()));
	}
	
	
	
	@After
	public void deleteAll() {
		new RestService().deleteAll();
	}


}

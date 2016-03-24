package data.daos;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {
	
	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private UserDao userDao;

	

	@Test
	public void testFindById() {
		Training training = new Training();
		trainingDao.save(training);
		assertEquals(training.getId(), trainingDao.findById(training.getId()).getId());
	}
	
	@Test
	public void testFindByTrainer(){
		User trainer1 = userDao.findByUsernameOrEmail("u1");
		User trainer2 = userDao.findByUsernameOrEmail("u2");
		Court court = new Court(1);
		Training training1 = new Training(trainer1, court, Calendar.getInstance(), Calendar.getInstance());
		trainingDao.save(training1);
		assertEquals(0, trainingDao.findByTrainer(trainer2).size());
		Training training2 = new Training(trainer2, court, Calendar.getInstance(), Calendar.getInstance());
		trainingDao.save(training2);
		assertEquals(1, trainingDao.findByTrainer(trainer2).size());
	}
	
	@Test
	public void testFindByDate(){
		User trainer1 = userDao.findByUsernameOrEmail("u1");
		Court court = new Court(2);
		Calendar beginDate = Calendar.getInstance();
		beginDate.add(Calendar.DAY_OF_MONTH, 1);
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		endDate.add(Calendar.HOUR_OF_DAY, 1);
		Training training = new Training(trainer1, court, beginDate, endDate);
		trainingDao.save(training);
		assertEquals(1, trainingDao.findByDate(training.getBeginDate()).size());
		
	}


}

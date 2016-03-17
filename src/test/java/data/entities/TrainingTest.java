package data.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrainingTest {

	@Test
	public void testAddStudents(){
		Training training = new Training();
		User user = new User();
		assertEquals(0, training.getStudents().size());
		training.addStudents(user);
		assertEquals(1, training.getStudents().size());
	}
	
	@Test
	public void testDeleteStudents(){
		Training training = new Training();
		User user = new User();
		assertEquals(0, training.getStudents().size());
		training.addStudents(user);
		assertEquals(1, training.getStudents().size());
		training.deleteStudents(user);
		assertEquals(0, training.getStudents().size());
	}
	
	
	
}

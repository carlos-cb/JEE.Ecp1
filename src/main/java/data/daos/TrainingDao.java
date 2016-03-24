package data.daos;


import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Training;
import data.entities.User;

public interface TrainingDao extends JpaRepository<Training, Integer>{

	Training findById(int id);
	
	List <Training> findByTrainer(User trainer);
	
	@Query(value="select * from Training training where training.beginDate = ?1", nativeQuery = true)
	List <Training> findByDate(Calendar beginDate);

	
	
	
	

}

package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@Controller
public class TrainingController {

	private TrainingDao trainingDao;
	private UserDao userDao;
	private CourtDao courtDao;
	
	@Autowired
	public void setTrainingDao(TrainingDao trainingDao){
		this.trainingDao = trainingDao;
	}
	
	@Autowired
    public void setCourtDao(CourtDao courtDao) {
        this.courtDao = courtDao;
    }
	
	@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
	
	public boolean creatTraining(TrainingWrapper trainingWrapper){
		if(!trainingDao.exists(trainingWrapper.getId())){
			Court court = courtDao.findOne(trainingWrapper.getCourtId());
			User trainer = userDao.getOne(trainingWrapper.getTrainerId());
			trainingDao.save(new Training(trainer, court, trainingWrapper.getBeginDate(), trainingWrapper.getEndDate()));
		}
		return true;
	}
	
	public List<TrainingWrapper> showTrainings(){
		List<TrainingWrapper> trainings = new ArrayList<>();
		for (Training training : trainingDao.findAll()){
			trainings.add(new TrainingWrapper(training));
		}
		return trainings;
	}
	
	public boolean deleteTraining(int id){
		Training training = trainingDao.findOne(id);
		if(training != null){
			trainingDao.delete(training);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean addTrainingStudent(int userId, int trainingId){
		User student = userDao.findOne(userId);
		Training training = trainingDao.findOne(trainingId);
		if(training != null && student != null){
			if(training.getStudents().size() < Training.MAXNUM_STUDENTS){
				training.addStudents(student);
				trainingDao.save(training);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean deleteTrainingStudent(int userId, int trainingId){
		User student = userDao.findOne(userId);
		Training training = trainingDao.findOne(trainingId);
		if(training.existStudent(userId)){
			training.deleteStudents(student);
			trainingDao.save(training);
			return true;
		}
		return false;
	}
	
	
}

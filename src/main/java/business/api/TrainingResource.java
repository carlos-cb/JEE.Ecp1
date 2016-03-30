package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import business.api.exceptions.AlreadyExistTrainingIdException;
import business.api.exceptions.NotFoundTrainingIdException;
import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;
import business.api.Uris;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {
	
	private TrainingController trainingController;
	
	@Autowired
	public void setTrainingController(TrainingController trainingController) {
		this.trainingController = trainingController;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void creatTraining(@RequestBody TrainingWrapper trainingWrapper) throws AlreadyExistTrainingIdException {
		if(!this.trainingController.creatTraining(trainingWrapper)){
			throw new AlreadyExistTrainingIdException(); 
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TrainingWrapper> showTrainings(){
		return trainingController.showTrainings();
	}
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
	public void deleteTraining(@PathVariable int id) throws NotFoundTrainingIdException{
		if(!trainingController.deleteTraining(id))
			throw new NotFoundTrainingIdException();
	}
	
	@RequestMapping(value = Uris.TRAINING_ID + Uris.USERS + Uris.USER_ID, method = RequestMethod.POST)
	public void addTrainingStudent(@PathVariable int trainingId, @PathVariable int userId)throws NotFoundTrainingIdException{
		if(!trainingController.addTrainingStudent(trainingId, userId)){
			throw new NotFoundTrainingIdException();
		}
	}
			
	@RequestMapping(value = Uris.TRAINING_ID + Uris.USERS + Uris.USER_ID, method = RequestMethod.DELETE)
	public void deleteTrainingStudent(@PathVariable int trainingId, @PathVariable int userId)throws NotFoundTrainingIdException{
		if(!trainingController.deleteTrainingStudent(trainingId, userId)){
			throw new NotFoundTrainingIdException();
		}
	}
	
	
}

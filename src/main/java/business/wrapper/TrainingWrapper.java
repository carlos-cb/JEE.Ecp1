package business.wrapper;

import java.util.Calendar;

import data.entities.Training;

public class TrainingWrapper {

	private int id;
	
	private Calendar beginDate;
	
	private Calendar endDate;
	
	private int trainerId;
	
	private int courtId;
	
	public TrainingWrapper(){
		
	}
	
	public TrainingWrapper(Calendar beginDate, Calendar endDate, int trainerId, int courtId){
		this.trainerId = trainerId;
		this.courtId = courtId;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public TrainingWrapper(Training training){
		this.id = training.getId();
		this.beginDate = training.getBeginDate();
		this.endDate = training.getEndDate();
		this.courtId = training.getCourt().getId();
		this.trainerId = training.getTrainer().getId(); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	@Override
	public String toString() {
		return "TrainingWrapper [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", trainerId="
				+ trainerId + ", courtId=" + courtId + "]";
	}


	
	
}

package data.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Training {
	
	@Id
	@GeneratedValue
	private int id;
	
	private Calendar beginDate;
	
	private Calendar endDate;
	
	@ManyToOne
	@JoinColumn
	private User trainer;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<User> students;
	
	@ManyToOne
	@JoinColumn
	private Court court;
	
	public static final int MAXNUM_STUDENTS = 4;
	
	public Training(){
		this.students = new ArrayList<User>();
	}
	
	public Training(User trainer, Court court, Calendar beginDate, Calendar endDate){
		this.trainer = trainer;
		this.court = court;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.students = new ArrayList<User>();
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

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public List<User> getStudents() {
		return students;
	}

	public boolean addStudents(User user) {
		if(this.students.size() < Training.MAXNUM_STUDENTS){
			this.students.add(user);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean deleteStudents(User user){
		return this.students.remove(user);
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", beginDate=" + beginDate + ", endDate=" + endDate + ", trainer=" + trainer
				+ ", students=" + students + ", court=" + court + "]";
	}
	
	
	
	
}

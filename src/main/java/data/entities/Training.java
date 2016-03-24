package data.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
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
	
	public Date getLessonTime(){
		return this.beginDate.getTime();
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
		String beDate = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(beginDate.getTime());
		String enDate = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(endDate.getTime());
		return "Training [id=" + id + ", beginDate=" + beDate + ", endDate=" + enDate + ", trainer=" + trainer.getUsername()
				+ ", students=" + students.toString() + ", court=" + court.getId() + "]";
	}
	
	
	
	
}

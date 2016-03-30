package api;


import java.util.Calendar;

import business.api.Uris;
import business.wrapper.TokenWrapper;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;

public class RestService {

    public static final String URL = "http://localhost:8080/JEE.Paddle.0.0.1-SNAPSHOT" + Uris.SERVLET_MAP;

    public void deleteAll() {
        new RestBuilder<TokenWrapper>(RestService.URL).path(Uris.ADMINS).basicAuth(this.loginAdmin(), "").delete().build();
    }

    public String loginAdmin() {
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("admin", "admin").clazz(TokenWrapper.class)
                .post().build();
        return token.getToken();
    }
    
    public String loginTrainer() {
		TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth("trainer", "trainer").clazz(TokenWrapper.class)
				.post().build();
		return token.getToken();
	}

    public String registerAndLoginPlayer() {
        UserWrapper player = new UserWrapperBuilder().build();
        new RestBuilder<Object>(URL).path(Uris.USERS).body(player).post().build();
        TokenWrapper token = new RestBuilder<TokenWrapper>(URL).path(Uris.TOKENS).basicAuth(player.getUsername(), player.getPassword())
                .clazz(TokenWrapper.class).post().build();
        return token.getToken();
    }
    
    public TrainingWrapper createTrainingWrapper(int trainerId, int courtId){
    	Calendar beginDate = Calendar.getInstance();
    	beginDate.add(Calendar.DAY_OF_MONTH, 5);
    	Calendar endDate = Calendar.getInstance();
    	endDate.add(Calendar.DAY_OF_MONTH, 15);
    	return new TrainingWrapper(beginDate, endDate, trainerId, courtId);
    }
    
    public void createCourt(String id) {
        new RestBuilder<Object>(URL).path(Uris.COURTS).param("id", id).basicAuth(this.loginAdmin(), "").post().build();
    }

    public void createTraining(TrainingWrapper trainingWrapper){
    	new RestBuilder<Object>(URL).path(Uris.TRAININGS).basicAuth(this.loginTrainer(), "").body(trainingWrapper)
    	.post().build();
    }
    
    public void deleteTraining(int id){
    	new RestBuilder<Object>(URL).path(Uris.TRAININGS).pathId(id).basicAuth(this.loginTrainer(), "")
    	.delete().build();
    }
    
    public void addStudent(int trainingId, int userId){
    	new RestBuilder<Object>(URL).path(Uris.TRAININGS).pathId(trainingId).path(Uris.USERS).pathId(userId)
    	.basicAuth(this.loginTrainer(), "").post().build();
    }
    
    public void deleteStudent(int trainingId, int userId){
	    new RestBuilder<Object>(URL).path(Uris.TRAININGS).pathId(trainingId).path(Uris.USERS).pathId(userId)
		.basicAuth(this.loginTrainer(), "").delete().build();
    }
}

package expenseTracker.repository.db;

import java.util.HashMap;

import expenseTracker.repository.dto.User;

public class Repository {
	private static Repository repo;
	private static User user;
	//private HashMap<Integer,String> expense;
	
	

	public static Repository getInstance() {
		if (repo == null) {
			return new Repository();
		}
		return repo;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		Repository.user = user;
	}
	
	

}

package Server;

public class User {
	public static final int RANK_AGENCY = 0;
	public static final int RANK_DOCTOR = 1;
	public static final int RANK_NURSE = 2;
	public static final int RANK_PATIENT = 3;
	
	protected String username;
	private int rank;
	private String division;

	
	
	public User(String username, int rank, String division) {
		this.username = username;
		this.rank = rank;
		this.division = division;
	}

	public String toString(){
		return username+";"+rank+";"+division+";";
	}


	public String getUserName() {
		return username;
	}

	public String welcomeMessage(){
		return "Welcome, generic user " + username +"!";
	}
		
}

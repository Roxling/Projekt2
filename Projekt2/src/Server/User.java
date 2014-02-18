package Server;

public class User {
	public static final int RANK_AGENCY = 0;
	public static final int RANK_DOCTOR = 1;
	public static final int RANK_NURSE = 2;
	public static final int RANK_PATIENT = 3;
	
	private String username;
	private String password;
	private int rank;
	private String division;

	
	
	public User(String username, String password, int rank, String division) {
		this.username = username;
		this.password = password;
		this.rank = rank;
		this.division = division;
	}

	public String toString(){
		return username+";"+password+";"+rank+";"+division+";";
	}
	
	public boolean authenticate(String password){
		return this.password.equals(password);
	}


	public String getUserName() {
		return username;
	}
}

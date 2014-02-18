package Server;

public class User {
	private String username;
	private String password;
	private int rank;
	private String division;
	
	public User(String line){
		int first = 0;
		int second = line.indexOf(';');
		username = line.substring(first, second);
		first = second;
		second = line.indexOf(';', second+1);
		password = line.substring(first+1, second);
		first = second;
		second = line.indexOf(';', second+1);
		rank = Integer.parseInt(line.substring(first+1, second));
		first = second;
		second = line.indexOf(';', second+1);
		division = line.substring(first+1, second);
		
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

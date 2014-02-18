package Server;

public class UserFactory {
	public static User createUser(String line){
		
		int first = 0;
		int second = line.indexOf(';');
		String username = line.substring(first, second);
		
		first = second;
		second = line.indexOf(';', second+1);
		String password = line.substring(first+1, second);
		
		
		first = second;
		second = line.indexOf(';', second+1);
		int rank = Integer.parseInt(line.substring(first+1, second));
		
		first = second;
		second = line.indexOf(';', second+1);
		String division = line.substring(first+1, second);
		
		switch(rank){
		case User.RANK_PATIENT:
			return new Patient(username,password,rank,division);
		default:
			return new User(username,password,rank,division);
		
		}
	}
}

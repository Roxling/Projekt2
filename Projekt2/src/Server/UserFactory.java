package Server;

public class UserFactory {
		private static String[] fields = {"CN=","OU=","O=","L"};
	public static User createUser(String line){
		try{
			String[] args = new String[3];
			for(int i = 0; i< 3; i++){
				int first = line.indexOf(fields[i]);
				int second = line.indexOf(fields[i+1]);;
				args[i] = line.substring(first+fields[i].length(),second-2);
			}
			
			int rank = Integer.parseInt(args[1]);
			switch(rank){
			case User.RANK_AGENCY:
				return null;
			case User.RANK_DOCTOR:
				return null;
			case User.RANK_NURSE:
				return null;
			case User.RANK_PATIENT:
				return new Patient(args[0],rank,args[2]);
			}
			
		}catch(Exception e){
			System.out.println("Wrong certformat!");
		}
		
		
		return null;
	}
}

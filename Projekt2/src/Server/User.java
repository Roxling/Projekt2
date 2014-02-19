package Server;

public abstract class User {
	public static final int RANK_AGENCY = 0;
	public static final int RANK_DOCTOR = 1;
	public static final int RANK_NURSE = 2;
	public static final int RANK_PATIENT = 3;
	
	protected String username;
	protected int rank;
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

	public boolean hasPermission(Command c) {
		switch(c.getCommandnum()){
		case Command.CREATE_COMMAND : return hasCreatePermission(c);
		case Command.READ_COMMAND : return hasReadPermission(c);
		case Command.REMOVE_COMMAND : return hasRemovePermission(c);
		case Command.WRITE_COMMAND : return hasWritePermission(c);
		}
		return false;
	}
	
	protected abstract boolean hasCreatePermission(Command c);
	protected abstract boolean hasRemovePermission(Command c);
	protected abstract boolean hasWritePermission(Command c);
	protected abstract boolean hasReadPermission(Command c);
		
}

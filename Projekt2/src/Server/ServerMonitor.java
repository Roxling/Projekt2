package Server;

import java.util.HashMap;

public class ServerMonitor {
	private HashMap<String,User> users = new HashMap<String,User>();
	
	public synchronized void addUser(User u){
		users.put(u.getUserName(), u);
	}
	
	public boolean authenticate(String username,String password){
		return users.get(username).authenticate(password);
	}
	
	
}

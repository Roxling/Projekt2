package Server;

import java.io.FileNotFoundException;
import java.util.HashMap;


public class ServerMonitor {
	HashMap<String,User> users = new HashMap<>();
	
	public synchronized void addUser(User u){
		users.put(u.getUserName(),u);
	}
	
	public synchronized User getUser(String name){
		return users.get(name);
	}

	public synchronized String execCommand(User user, Command c) {
		if(user.hasPermission(c)){
			try {
				return c.exec();
			} catch (FileNotFoundException e) {
				System.out.println("There is no such file");
			}
		}
		return "Permission denied";
	}
	
	
	
}

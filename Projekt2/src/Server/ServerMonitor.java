package Server;

import java.io.FileNotFoundException;


public class ServerMonitor {
	
	public synchronized void addUser(User u){
		
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

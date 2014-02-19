package Server;


public class ServerMonitor {
	
	public synchronized void addUser(User u){
		
	}

	public synchronized String execCommand(User user, Command c) {
		if(user.hasPermission(c)){
			return c.exec();
		}
		return "Permission denied";
	}
	
	
	
}

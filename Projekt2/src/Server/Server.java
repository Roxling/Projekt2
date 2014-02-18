package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Server {
	private ServerMonitor monitor;
	
	public static void main(String[] args) {
		(new Server()).run();
	}
	
	private void run(){
		readUsers();
		readRecords();
		acceptConnections();
	}
	
	private void acceptConnections() {
		// TODO Auto-generated method stub
		
	}

	private void readRecords() {
		// TODO Auto-generated method stub
		
	}

	private void readUsers(){
		Scanner scan = null;
		try {
			scan = new Scanner(new File("/h/d9/j/dat11vro/git/Projekt2/Projekt2/src/Server/usrlist"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor = new ServerMonitor();
		while(scan.hasNext()){
			User u = UserFactory.createUser(scan.nextLine());
			monitor.addUser(u);
		}
	}

}

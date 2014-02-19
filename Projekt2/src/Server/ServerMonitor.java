package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


public class ServerMonitor {
	private HashMap<String,User> users = new HashMap<>();
	private HashMap<String,MedicalRecord> records = new HashMap();
	private File logfile;
	private PrintWriter logger;
	
	public ServerMonitor(){
		logfile = new File("Server/log");
		Scanner scan = null;
		try {
			scan = new Scanner(logfile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		while(scan.hasNext()){
			sb.append(scan.nextLine());
		}
		
		sb.append("\n");

		try {
			logger = new PrintWriter(logfile);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		logger.println(sb.toString());
		
	}
	
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
	
	public synchronized MedicalRecord getRecord(String file){
		MedicalRecord med = records.get(file);
		if(med == null){
			med = new MedicalRecord(file);
			records.put(file,med);
		}
		return med;
	}
	
	public synchronized void addToLog(String command){
		logger.println(command);
	}
	
	public synchronized void closeLog(){
		logger.close();
	}
	
	
}

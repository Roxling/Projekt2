package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;


public class ServerMonitor {
	HashMap<String,User> users = new HashMap<>();
	HashMap<String,MedicalRecord> records = new HashMap();
	
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
		File f = new File("Server/log");
		if(!f.exists()) System.out.println("Server log does not exist.");
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder content = new StringBuilder();
		while(scan.hasNext()){
			content.append(scan.nextLine());
		}
		content.append("\n");
		content.append(command);
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(f);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		pw.write(content.toString());
		scan.close();
		pw.close();
	}
	
	
}

package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ServerMonitor {
	private HashMap<String,User> users = new HashMap<>();
	private HashMap<String,MedicalRecord> records = new HashMap();
	private Logger logger;
	
	public ServerMonitor(){
		logger = Logger.getLogger("Log");  
	    FileHandler fh;  
	    logger.setUseParentHandlers(false);
	    try {
	    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	        fh = new FileHandler("Logs/"+timeStamp+"-log.txt");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  


	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
	}
	
	public synchronized void addUser(User u){
		users.put(u.getUserName(),u);
	}
	
	public synchronized User getUser(String name){
		return users.get(name);
	}

	public synchronized String execCommand(User user, Command c) {
		if(!c.checkFile()) return "File not found or invalid arguments";
		if(user.hasPermission(c)){
			try {
				return c.exec();
			} catch (FileNotFoundException e) {;
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
		logger.info(command);
	}

	
	
}

package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AccessException;

public abstract class Command {
	public static final int CREATE_COMMAND = 0;
	public static final int REMOVE_COMMAND = 1;
	public static final int READ_COMMAND = 2;
	public static final int WRITE_COMMAND = 3;
	protected String arg;
	protected String filename;
	protected int commandnum = -1;
	public Command(String arg) throws AccessException{
		String args[] = CommandFactory.getArgs(arg);
		this.filename = args[0];
		this.arg = args[1];
	}
	
	public abstract String exec() throws FileNotFoundException;
	
	public int getCommandnum(){
		return commandnum;
	}
	
	protected boolean checkFile(){
		if(needFileCheck()){
			if(filename.length() > 0){
				File f = new File("Records/"+filename);
				return f.exists()&&!f.isDirectory();
			}
			return false;	
		}return true;
	}
	protected abstract boolean needFileCheck();
}

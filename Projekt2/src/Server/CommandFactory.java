package Server;

import java.rmi.AccessException;
import java.util.ArrayList;

public class CommandFactory {
	
	public static Command createCommand(String line){
		try{
			ArrayList<String> fields = new ArrayList<String>(4);
			fields.add(Command.CREATE_COMMAND,"create");
			fields.add(Command.REMOVE_COMMAND,"remove");
			fields.add(Command.READ_COMMAND,"read");
			fields.add(Command.WRITE_COMMAND,"write");
			boolean nocommand = false;
			int first = line.indexOf('-');
			int second = line.indexOf(' ', first+1);
			if(second == -1){
				second = line.length();
				nocommand = true;
			}
			String command = line.substring(first+1, second);
			command = command.toLowerCase();
			
			if(fields.contains(command) && nocommand) return new NoCommand("No arguments");
			String argument = line.substring(second+1);
			switch(fields.indexOf(command)){
			case Command.CREATE_COMMAND:
				return new CreateCommand(argument);
			case Command.REMOVE_COMMAND:
				return new RemoveCommand(argument);
			case Command.READ_COMMAND:
				return new ReadCommand(argument);
			case Command.WRITE_COMMAND:
				return new WriteCommand(argument);
			}
		}catch(Exception e){}
			try {
				return new NoCommand("Invalid command");
			} catch (AccessException e) {
				return null;
			}
	}
	
	public static String[] getArgs(String arg) throws AccessException{
		int index = 0;
		String args[] = new String[2];
		if(!arg.isEmpty() && arg.charAt(0) == ' '){
			while(index < arg.length() &&  arg.charAt(index) == ' ') index++;			
		}
		int split = arg.indexOf(' ',index);
		if(split == -1) split = arg.length();
		args[0] = arg.substring(index, split);
		if(args[0].contains("/")) throw new AccessException("Invalid filename");
		if(arg.length() > split+1){
			args[1] = arg.substring(split+1, arg.length());
		}else{
			args[1] = "";
		}
		return args;
	}

}

package Server;

import java.util.ArrayList;

public class CommandFactory {
	
	public static Command createCommand(String line){
		try{
			ArrayList<String> fields = new ArrayList<String>(4);
			fields.add(Command.CREATE_COMMAND,"create");
			fields.add(Command.REMOVE_COMMAND,"remove");
			fields.add(Command.READ_COMMAND,"read");
			fields.add(Command.WRITE_COMMAND,"write");
			
			int first = line.indexOf('-');
			int second = line.indexOf(' ', first+1);
			
			String command = line.substring(first+1, second);
			command = command.toLowerCase();
			
			String argument = line.substring(second+1);
			
			switch(fields.indexOf(command)){
			case Command.CREATE_COMMAND:
				return new CreateCommand(argument);
			case Command.REMOVE_COMMAND:
				return new CreateCommand(argument);
			case Command.READ_COMMAND:
				return new CreateCommand(argument);
			case Command.WRITE_COMMAND:
				return new CreateCommand(argument);
			}
		}catch(Exception e){}
		return new NoCommand("");
		
		
	}
	
	public static String getFileName(String arg){
		int index = 0;
		while(arg.charAt(index) == ' ') index++;
		String file = arg.substring(index, arg.indexOf(' ',index));
		
		return file;
	}
}

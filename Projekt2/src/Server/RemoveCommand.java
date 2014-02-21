package Server;

import java.io.File;
import java.rmi.AccessException;

public class RemoveCommand extends Command {

	public RemoveCommand(String arg) throws AccessException {
		super(arg);
		commandnum = Command.READ_COMMAND;
	}

	@Override
	public String exec() {
		File f = new File("Records/"+filename);
		if(!checkFile()) return "File not found";
		if(!f.exists()) return "File "+filename+" does not exist.";
		f.delete();
		return "File "+filename+" deleted";
	}

	@Override
	protected boolean needFileCheck() {
		return true;
	}

}

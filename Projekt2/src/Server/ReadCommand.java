package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.util.Scanner;

public class ReadCommand extends Command {

	public ReadCommand(String arg) throws AccessException {
		super(arg);
		commandnum = Command.READ_COMMAND;
	}

	@Override
	public String exec() throws FileNotFoundException {
		String retLine = "";
		if(!checkFile()) return "File not found";
		File f = new File("Records/"+filename);
		Scanner scan = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while(scan.hasNext()){
			sb.append(scan.nextLine());
			sb.append("\n");
		}
		scan.close();
		return sb.toString();
	}

	@Override
	protected boolean needFileCheck() {
		return true;
	}

}

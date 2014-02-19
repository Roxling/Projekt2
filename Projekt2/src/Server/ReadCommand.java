package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadCommand extends Command {

	public ReadCommand(String arg) {
		super(arg);
		commandnum = Command.READ_COMMAND;
	}

	@Override
	public String exec() throws FileNotFoundException {
		String retLine = "";
		File f = new File("Records/"+filename);
		if(!f.exists()) return "File "+filename+" not found";
		Scanner scan = new Scanner(f);
		StringBuilder sb = new StringBuilder();
		while(scan.hasNext()){
			sb.append(scan.nextLine());
			sb.append("\n");
		}
		scan.close();
		return sb.toString();
	}

}

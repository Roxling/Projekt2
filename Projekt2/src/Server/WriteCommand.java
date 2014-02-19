package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteCommand extends Command {

	public WriteCommand(String arg) {
		super(arg);
		commandnum = Command.WRITE_COMMAND;
	}

	@Override
	public String exec() {
		File f = new File("Records/"+filename);
		if(!f.exists()) return "File "+filename+" does not exist.";
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder content = new StringBuilder();
		while(scan.hasNext()){
			content.append(scan.nextLine());
		}
		content.append("\n");
		content.append(arg);
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(f);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		pw.write(content.toString());
		scan.close();
		pw.close();
		return "Success";
	}

}

package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateCommand extends Command {

	public CreateCommand(String arg) {
		super(arg);
	}

	@Override
	public String exec() {
		PrintWriter pw = null;
		String filepath = "Records/"+filename;
		File f = new File(filepath);
		if(f.exists()) return "File already exists";
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println(arg);
		pw.close();
		return filename+" created successfully";
	}

}

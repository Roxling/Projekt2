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
		try {
			pw = new PrintWriter(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}

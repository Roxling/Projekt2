package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadCommand extends Command {

	public ReadCommand(String arg) {
		super(arg);
		
	}

	@Override
	public String exec() throws FileNotFoundException {
		String retLine = "";
		try{
			BufferedReader reader = new BufferedReader(new FileReader("../../Records/" + arg));
			String line = null;
			while ((line = reader.readLine()) != null) {
			    retLine+=line;
			}
			
		}catch(Exception e){
			System.out.println(arg + " doesn't exist, or it is corrupt");
		}

		return retLine;
	}

}

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
			BufferedReader reader = new BufferedReader(new FileReader("../Projekt2/Records/" + this.filename));
			String line = null;
			System.out.println("hjaksdha");
			while ((line = reader.readLine()) != null) {
			    retLine+=(line+ "\n");
			}
			reader.close();
		}catch(Exception e){
			System.out.println(this.filename + " doesn't exist, or it is corrupt");
		}
		return retLine;
	}

}

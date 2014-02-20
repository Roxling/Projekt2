package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CreateCommand extends Command {

	public CreateCommand(String arg) {
		super(arg);
		commandnum = Command.CREATE_COMMAND;
	}

	@Override
	public String exec() {
		PrintWriter pw = null;
		if(!checkFile()) return "File not found";
		String filepath = "Records/"+filename;
		File f = new File(filepath);
		if(f.exists()) return "File already exists";
		if(!checkSyntax(arg)){
			return "Header not in valid format";
		}
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
	
	private boolean checkSyntax(String arg){
		int count=0;
		for(int i = 1; i< arg.length() ; i++){
			if(i >= arg.length()) return false;
			if(arg.charAt(i) == '/'){
				count++;
				i++;
			}
		}
		return count == 3;
	}

	@Override
	protected boolean needFileCheck() {
		return false;
	}

}

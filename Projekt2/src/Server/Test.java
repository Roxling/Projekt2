package Server;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		String line = "-create   testfile3.txt asd/s/s/as";
		String[] s = CommandFactory.getArgs(line);
		System.out.println(s[0] +" "+s[1]);
		
		Command c = CommandFactory.createCommand(line);
		
		try {
			System.out.println(c.exec());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

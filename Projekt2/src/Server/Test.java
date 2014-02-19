package Server;

public class Test {

	public static void main(String[] args) {
		String line = "-create   testfile.txt Patient1/Nurse1/Doctor2/Div1";
		String[] s = CommandFactory.getArgs(line);
		System.out.println(s[0] +" "+s[1]);
		
		Command c = CommandFactory.createCommand(line);
		System.out.println(c.exec());
	}

}

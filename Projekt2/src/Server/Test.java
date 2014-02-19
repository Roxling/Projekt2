package Server;

public class Test {

	public static void main(String[] args) {
		String line = "    testfile.txt gasd gsd asd tre";
		String s = CommandFactory.getFileName(line);
		System.out.println(s);
		
	}

}

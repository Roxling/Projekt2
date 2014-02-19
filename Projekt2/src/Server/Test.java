package Server;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) {
		MedicalRecord m = new MedicalRecord("testfile.txt");
		System.out.println(m);
	}

}

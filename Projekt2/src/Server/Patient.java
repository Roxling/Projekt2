package Server;

import java.util.ArrayList;

public class Patient extends User {
	
	private ArrayList<MedicalRecord> records;
	public Patient(String username, String password, int rank, String division) {
		super(username,password,rank,division);
		records = new ArrayList<MedicalRecord>();
		
	}
	
}
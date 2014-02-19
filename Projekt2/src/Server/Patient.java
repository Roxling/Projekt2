package Server;

import java.util.ArrayList;

public class Patient extends User {
	
	private ArrayList<MedicalRecord> records;
	public Patient(String username,int rank, String division) {
		super(username,rank,division);
		records = new ArrayList<MedicalRecord>();
		
	}
	
}

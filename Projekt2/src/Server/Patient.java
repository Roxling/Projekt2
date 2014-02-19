package Server;

import java.util.ArrayList;

public class Patient extends User {
	
	private ArrayList<MedicalRecord> records;
	public Patient(String username,int rank, String division,ServerMonitor mon) {
		super(username,rank,division,mon);
		records = new ArrayList<MedicalRecord>();
		
	}
	
	public String welcomeMessage(){
		return "Welcome, patient " + this.username;
	}

	@Override
	protected boolean hasCreatePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasRemovePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasWritePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasReadPermission(Command c) {
		MedicalRecord med = mon.getRecord(c.filename);
		return med.getPatient().equals(username.toLowerCase());
	}
	
}

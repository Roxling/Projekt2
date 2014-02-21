package Server;

import java.io.File;

public class Doctor extends User {

	public Doctor(String username, int rank, String division,ServerMonitor mon) {
		super(username, rank, division,mon);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean hasCreatePermission(Command c) {
		return true;
	}

	@Override
	protected boolean hasRemovePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasWritePermission(Command c) {
		MedicalRecord med = mon.getRecord(c.filename);
		return med.getDoctor().equals(username);
	}

	@Override
	protected boolean hasReadPermission(Command c) {
		MedicalRecord med = mon.getRecord(c.filename);
		if(med != null){
			return (med.getDivision().equals(division) || med.getDoctor().equals(username));
			
		}
		return false;
	}

}

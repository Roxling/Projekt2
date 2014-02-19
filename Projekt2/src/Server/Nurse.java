package Server;

public class Nurse extends User {

	public Nurse(String username, int rank, String division,ServerMonitor mon) {
		super(username, rank, division,mon);
		// TODO Auto-generated constructor stub
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
		MedicalRecord med = mon.getRecord(c.filename);
		return (med.getNurse().equals(username));
	}

	@Override
	protected boolean hasReadPermission(Command c) {
		MedicalRecord med = mon.getRecord(c.filename);
		return (med.getDivision().equals(division) || med.getNurse().equals(username));
	}

}

package Server;

public class Nurse extends User {

	public Nurse(String username, int rank, String division) {
		super(username, rank, division);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean hasCreatePermission(Command c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean hasRemovePermission(Command c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean hasWritePermission(Command c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean hasReadPermission(Command c) {
		// TODO Auto-generated method stub
		return false;
	}

}

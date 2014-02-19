package Server;

public class Doctor extends User {

	public Doctor(String username, int rank, String division,ServerMonitor mon) {
		super(username, rank, division,mon);
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

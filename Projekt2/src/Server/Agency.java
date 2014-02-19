package Server;

public class Agency extends User {

	public Agency(String username, int rank, String division) {
		super(username, rank, division);
	}

	@Override
	protected boolean hasCreatePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasRemovePermission(Command c) {
		return true;
	}

	@Override
	protected boolean hasWritePermission(Command c) {
		return false;
	}

	@Override
	protected boolean hasReadPermission(Command c) {
		return true;
	}

}

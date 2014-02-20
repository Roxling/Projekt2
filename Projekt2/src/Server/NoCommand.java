package Server;

public class NoCommand extends Command {
	private String msg;
	public NoCommand(String arg) {
		super(arg);
		msg = arg;
	}

	@Override
	public String exec() {
		return msg;
	}

	@Override
	protected boolean needFileCheck() {
		// TODO Auto-generated method stub
		return false;
	}

}

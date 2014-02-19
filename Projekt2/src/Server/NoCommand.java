package Server;

public class NoCommand extends Command {

	public NoCommand(String arg) {
		super(arg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String exec() {
		return "Invalid command or missing arguments";
	}

}

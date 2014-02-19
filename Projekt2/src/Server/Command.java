package Server;

public abstract class Command {
	public static final int CREATE_COMMAND = 0;
	public static final int REMOVE_COMMAND = 1;
	public static final int READ_COMMAND = 2;
	public static final int WRITE_COMMAND = 3;
	protected String arg;
	protected String filename;
	public Command(String arg){
		String args[] = CommandFactory.getArgs(arg);
		this.filename = args[0];
		this.arg = args[1];
	}
	
	public abstract String exec();
}

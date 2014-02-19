package Server;

public abstract class Command {
	public static final int CREATE_COMMAND = 0;
	public static final int REMOVE_COMMAND = 1;
	public static final int READ_COMMAND = 2;
	public static final int WRITE_COMMAND = 3;
	private String arg;
	public Command(String arg){
		this.arg = arg;
	}
	
	public abstract String exec();
}

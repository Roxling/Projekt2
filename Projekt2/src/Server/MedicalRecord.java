package Server;

public class MedicalRecord {
	private String[] accessholders;
	private String Entry;
	
	public MedicalRecord(String s){
		accessholders[0] = s.substring(0,s.indexOf("/"));
		accessholders[0] = s.substring(s.indexOf("/"),s.indexOf("/"));
		
	}
	
	public boolean checkPermission(){
		return true;
		
	}
	
}

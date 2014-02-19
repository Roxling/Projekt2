package Server;

public class MedicalRecord {
	private String[] accessholders;
	private String entry;
	
	public MedicalRecord(String filename){
		String s = null;
		
		int delimiter = 0;
		int delimiter2 = s.indexOf('/');
		for(int i = 0; i<4; i++){
			accessholders[i] = s.substring(delimiter,delimiter2-1);
			delimiter = delimiter2;
			delimiter2 = s.indexOf('/', delimiter+1);
		}
				
		
	}
	
	public boolean getPatient(String name, int rank){
		for(int i = rank; i<2; i++){
			if(name == accessholders[i]){
				return true;
			}
		}	
		return false;
		
	}
	
}

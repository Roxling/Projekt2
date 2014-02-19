package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MedicalRecord {
	private String file;
	private String patient;
	private String doctor;
	private String nurse;
	private String division;
	private String entry;
	
	public MedicalRecord(String filename){
		Scanner scan = null;
		File f = new File("Records/"+filename);
		if(f.exists()){
			try {
				scan = new Scanner(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String line = scan.nextLine();
			scan.close();
			extractInfo(line);
		}
		
	}

	private void extractInfo(String line){
		int first = 0;
		int second = line.indexOf("/");
		patient = line.substring(first, second);
		patient = patient.toLowerCase();
		first = second+1;
		second = line.indexOf("/", second+2);
		nurse = line.substring(first, second);
		nurse = nurse.toLowerCase();
		first = second+1;
		second = line.indexOf("/", second+2);
		doctor = line.substring(first, second);
		doctor = doctor.toLowerCase();
		first = second+1;
		second = line.length();
		division = line.substring(first, second);
		division = division.toLowerCase();
	}
	
	public String getPatient(){
		return patient;
	}
	
	public String getNurse(){
		return nurse;
	}
	
	public String getDoctor(){
		return doctor;
	}
	
	public String getDivision(){
		return division;
	}
	
	public String toString(){
		return patient+" "+nurse+" "+doctor+" "+division;
	}
}

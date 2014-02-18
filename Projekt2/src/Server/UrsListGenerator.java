package Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UrsListGenerator {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner scan = new Scanner(new File("/h/d9/j/dat11vro/git/Projekt2/Projekt2/src/Server/usrlist.clear"));
			PrintWriter pw = new PrintWriter(new File("/h/d9/j/dat11vro/git/Projekt2/Projekt2/src/Server/usrlist"));
			
			String line = scan.nextLine();
			while(scan.hasNext()){
				line = scan.nextLine();
				StringBuilder sb = new StringBuilder();
				int firstmark = line.indexOf(';');
				int secondmark = line.indexOf(';',firstmark+1);
				sb.append(line.substring(0, firstmark+1));
				String user = line.substring(0, firstmark);
				String pass = line.substring(firstmark+1, secondmark);
				sb.append(Hash.Crypt(pass, user));
				sb.append(line.substring(secondmark, line.length()));
				pw.println(sb.toString());
			}
			pw.close();
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(new File("/h/d9/j/dat11vro/git/Projekt2/Projekt2/src/Server/usrlist"));
		while(scan.hasNext()){
			System.out.println(scan.nextLine());
		}
	}

}

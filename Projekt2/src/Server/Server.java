package Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Server {

	public static void main(String[] args) {
		String s = "doc1";
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.update(s.getBytes());
		String encryptedString = new String(messageDigest.digest());
		System.out.println(encryptedString);

	}

}

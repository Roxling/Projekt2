package Security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	public static String Crypt(String password,String salt){
		       MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("MD5");
				digest.reset();
				digest.update(salt.getBytes());
				return new String(digest.digest(password.getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
}

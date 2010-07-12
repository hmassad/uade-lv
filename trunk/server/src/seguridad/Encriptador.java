package seguridad;

import java.security.MessageDigest;

public class Encriptador {

	public static String encriptar(String password) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes("UTF-8"));
		byte[] hash = messageDigest.digest();

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}
		return hexString.toString();
	}

}

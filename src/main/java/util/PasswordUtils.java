package util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeUtility;

public class PasswordUtils {

	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[_@#$%!]).{8,20})";
	
	private static MessageDigest digester;
	
	public PasswordUtils() {
		
	}

	private static byte[] encodeBase64(byte[] b) throws Exception {
	       ByteArrayOutputStream baos = new ByteArrayOutputStream();
	       OutputStream b64os = MimeUtility.encode(baos, "base64");
	       b64os.write(b);
	       b64os.close();
	       return baos.toByteArray();
	     }
	
	public static String shaHash(String password) {
		try {
			digester = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte encoded[] = null;
		try {
			digester.reset();
			digester.update(password.getBytes("UTF-8"));
			byte digest[] = digester.digest();
			encoded = encodeBase64(digest);
		} catch (Exception e) {
			return null;
		}
		String passwordShaHash = (new String(encoded));
		
		//TODO: se saca el salto de linea que se genera , ya que sino rompe al comprar con nuevas contraseñas.
		String saltoDeLinea = passwordShaHash.substring((passwordShaHash.length() -2));

		if(saltoDeLinea.equals("\r\n")){
			passwordShaHash =passwordShaHash.substring(0 , (passwordShaHash.length() -2));
		}
		
		return passwordShaHash;
	}
	
	public static boolean validate(final String password) {
		
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher;
		
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
}


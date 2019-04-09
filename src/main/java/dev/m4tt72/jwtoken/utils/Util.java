package dev.m4tt72.jwtoken.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import dev.m4tt72.jwtoken.models.Header;

public class Util {
	public static String base64Encode(String str) throws UnsupportedEncodingException {
		byte[] bytes = str.getBytes("UTF-8");
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
	
	public static String base64Decode(String str) {
		byte[] decoded = Base64.getUrlDecoder().decode(str);
		return new String(decoded);
	}
	
	public static String createHmac(Header header, String msg, String sec) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		byte[] key = sec.getBytes("UTF-8");
		String algorithm = "HmacSHA" + header.getAlg().substring(2);
		Mac hmac = Mac.getInstance(algorithm);
		SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
		hmac.init(keySpec);
		String result = bytesToHex(hmac.doFinal(msg.getBytes("UTF-8")));
		return base64Encode(result);
	}
	
	public static String bytesToHex(byte[] bytes) {
        final  char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

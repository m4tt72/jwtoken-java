package dev.m4tt72.jwtoken.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import dev.m4tt72.jwtoken.models.Header;

public class Util {
	
	public static String base64Encode(String str) {
		return base64Encode(str.getBytes());
	}

	public static String base64Encode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
	
	public static String base64Decode(String base64) {
		byte[] decoded = Base64.getUrlDecoder().decode(base64);
		return new String(decoded);
	}
	
	public static String createHmac(Header header, String msg, String sec) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] key = sec.getBytes();
		String algorithm = "HmacSHA" + header.getAlg().substring(2);
		Mac hmac = Mac.getInstance(algorithm);
		SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
		hmac.init(keySpec);
		String result = base64Encode(hmac.doFinal(msg.getBytes()));
		return result;
	}
	
}

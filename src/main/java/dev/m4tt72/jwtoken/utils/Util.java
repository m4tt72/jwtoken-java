package dev.m4tt72.jwtoken.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

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
	
	public static String createHmac(String alg, String msg, String key) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac hmac = Mac.getInstance(alg);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), alg);
		hmac.init(keySpec);
		return base64Encode(hmac.doFinal(msg.getBytes()));
	}
	
}

package dev.m4tt72.jwtoken;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import dev.m4tt72.jwtoken.algorithms.Algorithm;
import dev.m4tt72.jwtoken.exceptions.TokenExpiredException;
import dev.m4tt72.jwtoken.exceptions.TokenVerificationException;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Header;
import dev.m4tt72.jwtoken.models.Token;
import dev.m4tt72.jwtoken.utils.Util;

public class JWToken {

	public static Token sign(Claim claim, String secret, Algorithm algorithm) throws InvalidKeyException, NoSuchAlgorithmException  {
		if(secret.isEmpty() || secret == null) {
			throw new IllegalArgumentException("The Secret cannot be empty null");
		}
		Header header = new Header(algorithm);
		String encodedHeader = Util.base64Encode(header.toJson());
		String encodedPayload = Util.base64Encode(claim.toJson());
		String signature = Util.createHmac(header.getAlg(), encodedHeader + "." + encodedPayload, secret);
		Token token = new Token(encodedHeader + "." + encodedPayload + "." + signature);
		return token;
	}
	
	public static Claim verify(Token token, String secret) throws InvalidKeyException, NoSuchAlgorithmException {
		if(secret.isEmpty() || secret == null) {
			throw new IllegalArgumentException("The Secret cannot be empty null");
		}
		String header = Util.base64Decode(token.getHeader());
		String payload = Util.base64Decode(token.getPayload());
		String signature = token.getSignature();
		Header hdr = Header.fromJson(header);
		String currentSignature = Util.createHmac(hdr.getAlg(), Util.base64Encode(header) + "." + Util.base64Encode(payload), secret);
		if (!signature.equals(currentSignature)) {
			throw new TokenVerificationException();
		}
		Claim claim = Claim.fromJson(payload);
		if(claim.getExp().getTime() - new Date().getTime()<= 0) {
			throw new TokenExpiredException();
		}
		return claim;
	}
	
	public static Claim decode(Token token) {
		String payload = Util.base64Decode(token.getPayload());
		return Claim.fromJson(payload);
	}

}

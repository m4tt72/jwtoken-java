package dev.m4tt72.jwtoken;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import dev.m4tt72.jwtoken.exceptions.MalformedTokenException;
import dev.m4tt72.jwtoken.exceptions.TokenVerificationFailedException;
import dev.m4tt72.jwtoken.models.Header;
import dev.m4tt72.jwtoken.models.Claim;
import dev.m4tt72.jwtoken.models.Token;
import dev.m4tt72.jwtoken.utils.Util;

public class JWToken {

	public static Token sign(Claim claim, String secret, Header header) throws UnsupportedEncodingException,
			InvalidKeyException, NoSuchAlgorithmException, MalformedTokenException {
		return sign(claim.toString(), secret, header);
	}

	public static Token sign(String payload, String secret, Header header) throws UnsupportedEncodingException,
			InvalidKeyException, NoSuchAlgorithmException, MalformedTokenException {
		String encodedHeader = Util.base64Encode(header.toString());
		String encodedPayload = Util.base64Encode(payload);
		String signature = Util.createHmac(header, String.format("%s.%s", encodedHeader, encodedPayload), secret);
		Token token = new Token(String.format("%s.%s.%s", encodedHeader, encodedPayload, signature));
		return token;
	}

	public static Claim verify(Token token, String secret) throws InvalidKeyException, UnsupportedEncodingException,
			NoSuchAlgorithmException, TokenVerificationFailedException {
		String header = Util.base64Decode(token.getHeader());
		String payload = Util.base64Decode(token.getPayload());
		String signature = token.getSignature();
		Header hdr = Header.fromJson(header);
		String currentSignature = Util.createHmac(
			hdr,
			String.format("%s.%s", Util.base64Encode(header), Util.base64Encode(payload)), 
			secret
		);
		if (!signature.equals(currentSignature)) {
			throw new TokenVerificationFailedException();
		}
		Claim claim = Claim.fromJson(payload);
		return claim;
	}

}

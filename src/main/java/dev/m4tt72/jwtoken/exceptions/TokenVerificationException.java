package dev.m4tt72.jwtoken.exceptions;

public class TokenVerificationException extends JWTokenException {
	private static final long serialVersionUID = 1L;

	public TokenVerificationException() {
		super("Token verification failed, either the secret or the signature were invalid");
	}
}

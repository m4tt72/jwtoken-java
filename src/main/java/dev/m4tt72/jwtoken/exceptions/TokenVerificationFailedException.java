package dev.m4tt72.jwtoken.exceptions;

public class TokenVerificationFailedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Token verification failed, either the secret or the token were invalid";
	}
}

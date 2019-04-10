package dev.m4tt72.jwtoken.exceptions;

public class MalformedTokenException extends JWTokenException {

	private static final long serialVersionUID = 1L;

	public MalformedTokenException() {
		super("Malformed token");
	}
}

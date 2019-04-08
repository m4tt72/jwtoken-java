package dev.m4tt72.jwtoken.exceptions;

public class MalformedTokenException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Malformed token";
	}
}

package dev.m4tt72.jwtoken.exceptions;

public class JWTokenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    JWTokenException(String message) {
        super(message);
    }
}